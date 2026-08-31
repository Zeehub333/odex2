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
package com.odex.apps.account.service.move;

import com.odex.apps.account.db.Move;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.CancelReason;
import com.axelor.auth.db.User;

public interface MovePfpService {

  void refusalToPay(Move move, CancelReason reasonOfRefusalToPay, String reasonOfRefusalToPayStr);

  boolean isPfpButtonVisible(Move move, User user, boolean litigation) throws AxelorException;

  void setPfpStatus(Move move) throws AxelorException;

  boolean isValidatorUserVisible(Move move) throws AxelorException;
}
