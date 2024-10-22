package app.dao.interfaces;

import app.dto.InvoiceDetailDto;
import app.model.InvoiceDetail;

public interface InvoiceDetailDao {
  public void createInvoiceDetail(InvoiceDetail invoiceDetail)throws Exception;
}
