package com.ecom.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

import com.ecom.common.CommonMethodsDAO;
import com.ecom.config.EmailSenderService;
import com.ecom.domain.OrderInCartProdSummary;
import com.ecom.domain.OrderInCartSummary;
import com.ecom.domain.OrderInCartVarSummary;
import com.ecom.domain.OrderInfoDtl;
import com.ecom.domain.OrderInsertRequest;
import com.ecom.domain.ProductComboDtl;
import com.ecom.domain.ProductComboHdr;
import com.ecom.domain.ProductHdrDtl;
import com.ecom.domain.ProductSubHdrDtl;
import com.ecom.otp.OTPHandler;
import com.ecom.rowmapper.OrderInfoDtlRowMapper;
import com.ecom.rowmapper.OrderProdSummaryRowMapper;
import com.ecom.rowmapper.OrderSummaryRowMapper;
import com.ecom.rowmapper.OrderVarSummaryRowMapper;
import com.ecom.rowmapper.ProductComboDtlRowMapper;
import com.ecom.rowmapper.ProductComboHdrRowMapper;
import com.ecom.rowmapper.ProductHdrDtlRowMapper;
import com.ecom.rowmapper.ProductSubHdrDtlRowMapper;

@Repository
@Transactional
public class OrderDAO {

	private static final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
	@Autowired
	JdbcTemplate jdbc;
	
	 @Autowired
	 private EmailSenderService emailSenderService;

	public String getInCartOrderCount(String vendorId, String customerId) {
		String returnCnt = "0";
		try {
			BigDecimal oCnt=BigDecimal.ZERO;
			String getQ = "select sum(dtl.QUANTITY) from order_information_hdr hdr , order_information_dtl dtl where hdr.CD_ID =? and hdr.VENDOR_ID =? and hdr.OI_ID=dtl.OI_ID  and hdr.ORDER_STATUS=?";
			oCnt = this.jdbc.queryForObject(getQ, new Object[] { customerId, vendorId, "ST001" }, BigDecimal.class);
			if(oCnt==null) {
				returnCnt="0";
			}else {
				returnCnt=oCnt.toString();
			}
			
		} catch (Exception ex) {
			logger.error("getInCartOrderCount DAO method exception" + ex);
		}
		return returnCnt;
	}

	public List<OrderInCartSummary> getInCartProduct(String vendorId, String customerId) {
		List<OrderInCartSummary> InCart = null;
		List<OrderInCartProdSummary> prod = null;
		try {

			String getOrderQ = "SELECT ORDER_CODE,OI_ID,CDE_ID,GROSS_PRICE,COMBO_PRICE,DISCOUNTED_PRICE,NETT_PRICE FROM order_information_hdr where CD_ID = ? and VENDOR_ID=? and ORDER_STATUS=?;";
			RowMapper<OrderInCartSummary> rowMapper = new OrderSummaryRowMapper();
			InCart = this.jdbc.query(getOrderQ, rowMapper, customerId, vendorId, "ST001");

			if (InCart.size() > 0) {
				getOrderQ = "SELECT \r\n" + "    dtl.PROD_DESC,\r\n" + "    dtl.PROD_CODE,\r\n"
						+ "    phdr.COM_DESC,\r\n" + "    dtl.OID_DTL,\r\n" + "    dtl.SUB_DESC,\r\n"
						+ "    dtl.QUANTITY,\r\n" + "    dtl.PRICE,\r\n" + "    st.STATUS_DESC,\r\n"
						+ "    Date(hdr.ORDER_DATETIME) as ORDER_DATETIME,\r\n"
						+ "    Date(hdr.ACTUAL_DELIVERY_DATE) as ACTUAL_DELIVERY_DATE , dtl.PS_ID \r\n" + "    \r\n" + "FROM\r\n"
						+ "    order_information_hdr hdr,\r\n" + "    status_mst st,\r\n"
						+ "    order_information_dtl dtl\r\n" + "        LEFT JOIN\r\n"
						+ "    product_combo_hdr phdr ON phdr.PCH_ID = dtl.PCH_ID\r\n" + "WHERE\r\n"
						+ "    hdr.OI_ID = dtl.OI_ID AND hdr.CD_ID = ?\r\n" + "        AND hdr.VENDOR_ID = ?\r\n"
						+ "        and hdr.ORDER_STATUS = ?\r\n" + "        and st.STATUS_CODE = hdr.ORDER_STATUS ";
				RowMapper<OrderInCartProdSummary> rowMapper1 = new OrderProdSummaryRowMapper();
				prod = this.jdbc.query(getOrderQ, rowMapper1, customerId, vendorId, "ST001");

				for (int h = 0; h < prod.size(); h++) {
					String getQ = "SELECT \r\n" + "  distinct  ty.VARIENT_TYPE_DESC,\r\n" + "    dtl.VALUE\r\n" + "FROM\r\n"
							+ "    order_information_varient_dtl dtl,\r\n" + "    product_varient_type ty\r\n"
							+ "WHERE\r\n" + "    dtl.OID_DTL = ?\r\n"
							+ "        AND ty.VARIENT_TYPE_CODE = dtl.VARIENT_TYPE_CODE";
					RowMapper<OrderInCartVarSummary> rowMapper2 = new OrderVarSummaryRowMapper();
					prod.get(h).setOrderVarSumm(this.jdbc.query(getQ, rowMapper2, prod.get(h).getOrderDtlId()));
				}
				InCart.get(0).setProdDet(prod);
			}

		} catch (Exception ex) {
			logger.error("getInCartProduct DAO method exception" + ex);
		}
		return InCart;
	}

	public String updateCartDelAddress(String vendorId, String customerId, String addressId) {
		String returnMsg = "";
		try {
			String upQ = "update order_information_hdr set CDE_ID = ? where CD_ID=? and VENDOR_ID=? and ORDER_STATUS=?";
			this.jdbc.update(upQ, addressId, customerId, vendorId, "ST001");
		} catch (Exception ex) {
			logger.error("updateCartDelAddress DAO method exception" + ex);
		}
		return returnMsg;
	}

	public String confirmCart(String vendorId, String customerId) {
		String returnMsg = "";
		try {
			List<OrderInCartSummary> InCart = null;
			List<OrderInCartProdSummary> prod = null;
			String getOrderQ = "SELECT ORDER_CODE,OI_ID,CDE_ID,GROSS_PRICE,COMBO_PRICE,DISCOUNTED_PRICE,NETT_PRICE FROM order_information_hdr where CD_ID = ? and VENDOR_ID=? and ORDER_STATUS=?;";
			RowMapper<OrderInCartSummary> rowMapper = new OrderSummaryRowMapper();
			InCart = this.jdbc.query(getOrderQ, rowMapper, customerId, vendorId, "ST001");
			
			getOrderQ = "SELECT \r\n" + "    dtl.PROD_DESC,\r\n" + "    dtl.PROD_CODE,dtl.PS_ID,\r\n"
					+ "    phdr.COM_DESC,\r\n" + "    dtl.OID_DTL,\r\n" + "    dtl.SUB_DESC,\r\n"
					+ "    dtl.QUANTITY,\r\n" + "    dtl.PRICE,\r\n" + "    st.STATUS_DESC,\r\n"
					+ "    Date(hdr.ORDER_DATETIME) as ORDER_DATETIME,\r\n"
					+ "    Date(hdr.ACTUAL_DELIVERY_DATE) as ACTUAL_DELIVERY_DATE\r\n" + "    \r\n" + "FROM\r\n"
					+ "    order_information_hdr hdr,\r\n" + "    status_mst st,\r\n"
					+ "    order_information_dtl dtl\r\n" + "        LEFT JOIN\r\n"
					+ "    product_combo_hdr phdr ON phdr.PCH_ID = dtl.PCH_ID\r\n" + "WHERE\r\n"
					+ "    hdr.OI_ID = dtl.OI_ID AND hdr.CD_ID = ?\r\n" + "        AND hdr.VENDOR_ID = ?\r\n"
					+ "        and hdr.ORDER_STATUS = ?\r\n" + "        and st.STATUS_CODE = hdr.ORDER_STATUS ";
			RowMapper<OrderInCartProdSummary> rowMapper1 = new OrderProdSummaryRowMapper();
			prod = this.jdbc.query(getOrderQ, rowMapper1, customerId, vendorId, "ST001");
			StringJoiner pj=new StringJoiner(",");
			for(OrderInCartProdSummary o:prod) {
				pj.add(o.getProdDesc());
			}
			
			
			String upQ = "update order_information_hdr set ORDER_STATUS = ? where CD_ID=? and VENDOR_ID=? and ORDER_STATUS=?";
			this.jdbc.update(upQ, "ST002", customerId, vendorId, "ST001");
			String getContactNo="select concat_ws('|',PRIMARY_CONTACT_NO,NAME) from customer_details where CD_ID=?";
			String mob=this.jdbc.queryForObject(getContactNo, new Object[] {  customerId }, String.class);
			OTPHandler.sentCustomerMessage(mob.split("\\|")[0], vendorId, "OrderConfirm"+"|"+pj.toString()+"|"+InCart.get(0).getOrderCode(), this.jdbc);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("mubeen.ansaramm@gmail.com");
            mailMessage.setSubject("Order confirmation");
            mailMessage.setFrom("regimtestmail@gmail.com");
           
            mailMessage.setText("New order received with order code : "
            +InCart.get(0).getOrderCode()+" Containing-" +pj.toString()+" Order amount Rs."+InCart.get(0).getSubTotal()+" From "+mob.replace('|', ','));
            emailSenderService.sendEmail(mailMessage);
			logger.info("mail sent");
			returnMsg = InCart.get(0).getOrderCode();
		} catch (Exception ex) {
			logger.error("confirmCart DAO method exception" + ex);
		}
		return returnMsg;
	}

	public String deleteCartItem(String vendorId, String customerId, String itemId) {
		String returnMsg = "";
		try {

			int isUp = 0;
			String getOrderCbQ = "select concat(PCH_ID,\"|\",OI_ID) from order_information_dtl where OID_DTL = ?";
			String orderDtlId = this.jdbc.queryForObject(getOrderCbQ, new Object[] { itemId }, String.class);
			String arr[] = orderDtlId.split("\\|");
			String getOrderInfo = "select * from order_information_dtl where OID_DTL=?";
			String updateHdrPrice = "update order_information_hdr set GROSS_PRICE=GROSS_PRICE-? where OI_ID=? ";
			String upDetl = "DELETE FROM order_information_dtl WHERE OID_DTL=? ";
			List<OrderInfoDtl> pcdList = this.jdbc.query(getOrderInfo, new Object[] { itemId },
					new OrderInfoDtlRowMapper());
			String oId = pcdList.get(0).getOid();
			String pchid = pcdList.get(0).getPchId();
			if (arr[0].equalsIgnoreCase("0")) {
				String upQ = "DELETE FROM order_information_varient_dtl WHERE OID_DTL=? ";
				this.jdbc.update(upQ, itemId);

				float price = Float.parseFloat(pcdList.get(0).getQty()) * Float.parseFloat(pcdList.get(0).getPrice());
				this.jdbc.update(updateHdrPrice, price, oId);
				upQ = "DELETE FROM order_information_dtl WHERE OID_DTL=?";
				this.jdbc.update(upQ, itemId);
				isUp = this.jdbc.update(upDetl, itemId);
			} else {
				String upQ = "delete from order_information_varient_dtl where OID_DTL in (select OID_DTL from order_information_dtl where PCH_ID=? and OI_ID=?)";
				this.jdbc.update(upQ, arr[0], arr[1]);

				String getComboSum = "select sum(PRICE*QUANTITY) from order_information_dtl where OI_ID=? and PCH_ID=?";
				BigDecimal sum = this.jdbc.queryForObject(getComboSum, new Object[] { oId, pchid }, BigDecimal.class);
				String updateHdr = "update order_information_hdr set GROSS_PRICE=GROSS_PRICE-?,COMBO_PRICE=COMBO_PRICE-? where OI_ID=?";
				this.jdbc.update(updateHdr, sum, sum, oId);

				String deltOrderDtl = "delete from order_information_dtl where OI_ID=? and PCH_ID=?";
				isUp = this.jdbc.update(deltOrderDtl, oId, pchid);
			}
			if (isUp > 0) {
				returnMsg = "Item deleted";
				String deleteUnwantedHdr = "delete from order_information_hdr where OI_ID not in (select OI_ID from order_information_dtl)";
				this.jdbc.update(deleteUnwantedHdr);
			}

		} catch (Exception ex) {
			logger.error("deleteCartItem DAO method exception" + ex);
		}
		return returnMsg;
	}

	public List<OrderInCartProdSummary> getAccInCartProduct(String vendorId, String customerId) {
		List<OrderInCartProdSummary> prod = null;
		try {
			String getOrderQ = "SELECT \r\n" + "    dtl.PROD_DESC,\r\n" + "    dtl.PROD_CODE,\r\n"
					+ "    phdr.COM_DESC,\r\n" + "    dtl.OID_DTL,\r\n" + "    dtl.SUB_DESC,\r\n"
					+ "    dtl.QUANTITY,\r\n" + " dtl.PS_ID,   dtl.PRICE,\r\n" + "    st.STATUS_DESC,\r\n"
					+ "    Date(hdr.ORDER_DATETIME) as ORDER_DATETIME,\r\n"
					+ "    Date(hdr.ACTUAL_DELIVERY_DATE) as ACTUAL_DELIVERY_DATE \r\n" + "    \r\n" + "FROM\r\n"
					+ "    order_information_hdr hdr,\r\n" + "    status_mst st,\r\n"
					+ "    order_information_dtl dtl\r\n" + "        LEFT JOIN\r\n"
					+ "    product_combo_hdr phdr ON phdr.PCH_ID = dtl.PCH_ID\r\n" + "WHERE\r\n"
					+ "    hdr.OI_ID = dtl.OI_ID AND hdr.CD_ID = ?\r\n" + "        AND hdr.VENDOR_ID = ?\r\n"
					+ "        and st.STATUS_CODE = hdr.ORDER_STATUS order by ORDER_DATETIME";
			RowMapper<OrderInCartProdSummary> rowMapper1 = new OrderProdSummaryRowMapper();
			prod = this.jdbc.query(getOrderQ, rowMapper1, customerId, vendorId);

			for (int h = 0; h < prod.size(); h++) {
				String getQ = "SELECT \r\n" + "   ptype.VARIENT_TYPE_DESC,\r\n" + "   dtl.VALUE\r\n" + "FROM\r\n"
						+ "    customer_wishlist_varient_dtl dtl,\r\n" + "    product_varient_type ptype\r\n"
						+ "WHERE\r\n" + "    ptype.VARIENT_TYPE_CODE = dtl.VARIENT_TYPE_CODE \r\n"
						+ "    and CWD_ID = ?";
				RowMapper<OrderInCartVarSummary> rowMapper2 = new OrderVarSummaryRowMapper();
				prod.get(h).setOrderVarSumm(this.jdbc.query(getQ, rowMapper2, prod.get(h).getOrderDtlId()));
			}
		} catch (Exception ex) {
			logger.error("getAccInCartProduct DAO method exception" + ex);
		}
		return prod;
	}

	public String addToCart(OrderInsertRequest orderInsertRequest) {
		String retMsg = "";
		try {

			String cdId = orderInsertRequest.getCdId();
			String vendorId = orderInsertRequest.getVendorId();
			String psId = orderInsertRequest.getPsId();
			String isCombo = orderInsertRequest.getIsCombo();
			String pchId = orderInsertRequest.getPchId();
			String qty = orderInsertRequest.getQty();
			String discountedPrice="0";
			LocalDateTime upDate=java.time.LocalDateTime.now();
			String getOrderQ = "SELECT OI_ID,CDE_ID,ORDER_CODE,GROSS_PRICE,COMBO_PRICE,DISCOUNTED_PRICE,NETT_PRICE FROM order_information_hdr where CD_ID = ? and VENDOR_ID=? and ORDER_STATUS=?;";
			RowMapper<OrderInCartSummary> rowMapper = new OrderSummaryRowMapper();
			List<OrderInCartSummary> inCartList = this.jdbc.query(getOrderQ, rowMapper, cdId, vendorId, "ST001");
			String getProdByPsid = "SELECT \r\n" + "    pm.*, bm.*, pc.*, ps.*\r\n" + "FROM\r\n"
					+ "    product_details pm,\r\n" + "    brand_mst bm,\r\n" + "    product_category_hdr pc,\r\n"
					+ "    product_sub_hdr ps\r\n" + "WHERE\r\n" + "    pm.BM_ID = bm.BM_ID\r\n"
					+ "        AND pm.PARENT_CATE_HDR_ID = pc.PARENT_CATE_HDR_ID\r\n"
					+ "        AND ps.PROD_ID = pm.PROD_ID and ps.PS_ID=?";

			String insordDtl = "insert into order_information_dtl(PS_ID,OI_ID,PCH_ID,PROD_CODE,PROD_NAME,PROD_DESC,SUB_DESC,PRICE,QUANTITY) values(?,?,?,?,?,?,?,?,?)";
			String upHdrValues = "update order_information_hdr set CDE_ID=?,ORDER_DATETIME=?,UPDATED_DATETIME=?,GROSS_PRICE=GROSS_PRICE+?,NETT_PRICE=NETT_PRICE+?,DISCOUNTED_PRICE=DISCOUNTED_PRICE+? where OI_ID=?";
			String insPsubTble = "insert into order_information_varient_dtl (OID_DTL,VARIENT_TYPE_CODE,VALUE) values(?,?,?)";
			String insHdrQry = "insert into order_information_hdr (CD_ID,VENDOR_ID,ORDER_CODE,ORDER_STATUS,ORDER_DATETIME) values(?,?,?,?,?)";
			String oid = "";
			int cdeId=0;
			String getCdeCnt="select count(*) from customer_details_extn where CD_ID=?";
			int CdeCnt = this.jdbc.queryForObject(getCdeCnt, new Object[] {  cdId }, Integer.class);
			if(CdeCnt>0) {
				String getCde="select CDE_ID from customer_details_extn where CD_ID=? limit 1";
				 cdeId = this.jdbc.queryForObject(getCde, new Object[] {  cdId }, Integer.class);
			}
			// If there is an existing item in cart
			if (inCartList.size() > 0) {
				oid = inCartList.get(0).getoId();
			} else {
				String orderCode=CommonMethodsDAO.generateOrderId(cdId, vendorId, this.jdbc);
				KeyHolder hdrHolder = new GeneratedKeyHolder();
				jdbc.update(connection -> {
					PreparedStatement ps = connection.prepareStatement(insHdrQry, new String[] { "ID" });
					ps.setString(1, cdId);
					ps.setString(2, vendorId);
					ps.setString(3, orderCode);
					ps.setString(4, "ST001");
					ps.setString(5, upDate+"");
					return ps;
				}, hdrHolder);

				int hdrId = hdrHolder.getKey().intValue();
				oid = hdrId + "";
			}
			final String fOid = oid;
			String checkProdExist="select count(*) from order_information_dtl where PROD_CODE=? and OI_ID=?";
			// For non combo add to cart
			if (isCombo.equalsIgnoreCase("0")) {
				List<ProductHdrDtl> pList = null;
				// Getting product details from psId
				pList = this.jdbc.query(getProdByPsid, new Object[] { psId }, new ProductHdrDtlRowMapper());
				if(!pchId.equalsIgnoreCase("0")) {
					String getPriceFromCombo="select concat(ACTUAL_RATE,'|',DISCOUNT_RATE) from product_combo_hdr where PCH_ID=?";
					String dicDate=this.jdbc.queryForObject(getPriceFromCombo, new Object[] {  pchId }, String.class);
					pList.get(0).setPrice(dicDate.split("\\|")[0]);
					discountedPrice=dicDate.split("\\|")[1];
				}
				BigDecimal itemPrice = new BigDecimal(qty).multiply(new BigDecimal(pList.get(0).getPrice()));

				final String fProdCode = pList.get(0).getProdCode();
				final String fprodDesc = pList.get(0).getProdDesc();
				final String fDtlDesc = pList.get(0).getDtlDesc();
				final BigDecimal fProce = itemPrice;
				final String fQty = qty;
                
                int pCnt = this.jdbc.queryForObject(checkProdExist, new Object[] {  fProdCode,fOid }, Integer.class);
                int insDtl=0;
                //If its an existing product update qty
                if(pCnt>0) {
                	String getDtlId="select OID_DTL from order_information_dtl where PROD_CODE=? and OI_ID=? order by OI_ID desc limit 1";
                	insDtl=this.jdbc.queryForObject(getDtlId, new Object[] {  fProdCode,fOid }, Integer.class);
                	String upCartDtl="update order_information_dtl set QUANTITY=QUANTITY+?,PRICE=? where OID_DTL=? ";
                	this.jdbc.update(upCartDtl,fQty,fProce,insDtl);
                }else {
				// Inserting product list in order detail table
                	final String iPrice=pList.get(0).getPrice();
				KeyHolder orderDtlHolder = new GeneratedKeyHolder();
				jdbc.update(connection -> {
					PreparedStatement ps = connection.prepareStatement(insordDtl, new String[] { "ID" });
					ps.setString(1, psId);
					ps.setString(2, fOid);
					ps.setString(3, pchId);
					ps.setString(4, fProdCode);
					ps.setString(5, fprodDesc);
					ps.setString(6, fprodDesc);
					ps.setString(7, fDtlDesc);
					ps.setString(8,iPrice);
					ps.setString(9, fQty);
					return ps;
				}, orderDtlHolder);

				 insDtl = orderDtlHolder.getKey().intValue();
                }
				if (insDtl > 0) {
					String getPsDtlQry = "SELECT * FROM product_sub_hdr ph,product_sub_dtl ps,product_varient_type pv where ph.PS_ID=ps.PS_ID and ps.VARIENT_TYPE_CODE=pv.VARIENT_TYPE_CODE and ph.PS_ID=?";
					List<ProductSubHdrDtl> psList = this.jdbc.query(getPsDtlQry, new Object[] { psId },
							new ProductSubHdrDtlRowMapper());
					// Inserting values in order varient table
					for (int s = 0; s < psList.size(); s++) {
						this.jdbc.update(insPsubTble,
								new Object[] { insDtl, psList.get(s).getVarientTypeCode(), psList.get(s).getValue() });
					}
					int upHdr = this.jdbc.update(upHdrValues,
							new Object[] { cdeId,upDate,upDate,itemPrice, itemPrice,discountedPrice, fOid });
					if (upHdr > 0) {
						retMsg = getInCartOrderCount(vendorId,cdId+"");
					}
				}

			} // For combo add to cart
			else {

				String getProductComboHdr = "select * from product_combo_hdr where VENDOR_ID=? and PCH_ID=? and IS_ACTIVE=1";
				// Getting combo header details for price details
				List<ProductComboHdr> phList = this.jdbc.query(getProductComboHdr, new Object[] { vendorId, pchId },
						new ProductComboHdrRowMapper());
				BigDecimal comboPrice = new BigDecimal(phList.get(0).getActualRate());
				BigDecimal discPrice = new BigDecimal(phList.get(0).getDiscountRate());
				String type = phList.get(0).getComType();

				String getComboDtlQry = "select * from product_combo_dtl pd,product_details pm where pd.PROD_ID=pm.PROD_ID and pd.PCH_ID=?";
				List<ProductComboDtl> pcdList = this.jdbc.query(getComboDtlQry, new Object[] { pchId },
						new ProductComboDtlRowMapper());
				// Iterating combo products
				for (int j = 0; j < pcdList.size(); j++) {

					List<ProductHdrDtl> pList = null;
					pList = this.jdbc.query(getProdByPsid, new Object[] { pcdList.get(j).getPsId() },
							new ProductHdrDtlRowMapper());
					final String cPsid= pcdList.get(j).getPsId();
					final String fProdCode = pList.get(0).getProdCode();
					final String fprodDesc = pList.get(0).getProdDesc();
					final String fDtlDesc = pList.get(0).getDtlDesc();
					final String fProce = pList.get(0).getPrice();
					final String fQty = qty;

					KeyHolder orderDtlHolder = new GeneratedKeyHolder();
					jdbc.update(connection -> {
						PreparedStatement ps = connection.prepareStatement(insordDtl, new String[] { "ID" });
						ps.setString(1, cPsid);
						ps.setString(2, fOid);
						ps.setString(3, pchId);
						ps.setString(4, fProdCode);
						ps.setString(5, fprodDesc);
						ps.setString(6, fprodDesc);
						ps.setString(7, fDtlDesc);
						ps.setString(8, fProce);
						ps.setString(9, fQty);
						return ps;
					}, orderDtlHolder);

					int insDtl = orderDtlHolder.getKey().intValue();
					if (insDtl > 0) {
						String getPsDtlQry = "SELECT * FROM product_sub_hdr ph,product_sub_dtl ps,product_varient_type pv where ph.PS_ID=ps.PS_ID and ps.VARIENT_TYPE_CODE=pv.VARIENT_TYPE_CODE and ph.PS_ID=?";
						List<ProductSubHdrDtl> psList = this.jdbc.query(getPsDtlQry, new Object[] { cPsid },
								new ProductSubHdrDtlRowMapper());
						for (int s = 0; s < psList.size(); s++) {
							this.jdbc.update(insPsubTble, new Object[] { insDtl,
									psList.get(s).getVarientTypeCode(), psList.get(s).getValue() });
						}
						// For combo and Discount updating header
						int hdrUpRes = 0;
						if (j == 0) {
							// For combo
							if (type.equalsIgnoreCase("PC001")) {
								String upHdrQry = "update order_information_hdr set CDE_ID=?,ORDER_DATETIME=?,UPDATED_DATETIME=?,IS_DISCOUNT=0,COMBO_PRICE=COMBO_PRICE+?,GROSS_PRICE=GROSS_PRICE+?,NETT_PRICE=NETT_PRICE+? where OI_ID=?";
								hdrUpRes = this.jdbc.update(upHdrQry,
										new Object[] {cdeId,upDate,upDate, comboPrice, comboPrice, comboPrice, comboPrice, fOid });
							} // For Dicount
							else {
								String upHdrQry = "update order_information_hdr set CDE_ID=?,ORDER_DATETIME=?,UPDATED_DATETIME=?,IS_DISCOUNT=1,COMBO_PRICE=COMBO_PRICE+?,DISCOUNTED_PRICE=DISCOUNTED_PRICE+?,GROSS_PRICE=GROSS_PRICE+?,NETT_PRICE=NETT_PRICE+? where OI_ID=?";
								hdrUpRes = this.jdbc.update(upHdrQry,
										new Object[] {cdeId,upDate,upDate, comboPrice, discPrice, comboPrice, comboPrice, fOid });
							}
						}
						if (hdrUpRes > 0) {
							retMsg = getInCartOrderCount(vendorId,cdId+"");
						}

					}
				}
			}
		} catch (Exception e) {
			logger.error("addToCart DAO error-->" + e);
			e.printStackTrace();
		}
		return retMsg;
	}

}
