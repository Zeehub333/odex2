/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2026 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.odex.apps.account.service;

import com.odex.apps.account.db.Invoice;
import com.odex.apps.account.db.MoveLine;
import com.odex.apps.account.db.Reconcile;
import com.odex.apps.account.db.TaxLine;
import com.odex.apps.account.db.TaxPaymentMoveLine;
import com.odex.apps.base.AxelorException;
import java.math.BigDecimal;

public interface TaxPaymentMoveLineService {

  public TaxPaymentMoveLine computeTaxAmount(TaxPaymentMoveLine taxPaymentMoveLine)
      throws AxelorException;

  public TaxPaymentMoveLine getReverseTaxPaymentMoveLine(TaxPaymentMoveLine taxPaymentMoveLine)
      throws AxelorException;

  TaxPaymentMoveLine createTaxPaymentMoveLineWithFixedAmount(
      Invoice invoice,
      BigDecimal paymentRatio,
      int vatSystemSelect,
      MoveLine invoiceMoveLine,
      TaxLine taxLine,
      MoveLine customerPaymentMoveLine,
      Reconcile reconcile);
}
