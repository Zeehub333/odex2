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
package com.odex.apps.account.module;

import com.axelor.app.AxelorModule;
import com.odex.apps.account.db.Invoice;
import com.odex.apps.account.db.repo.AccountAccountRepository;
import com.odex.apps.account.db.repo.AccountManagementAccountRepository;
import com.odex.apps.account.db.repo.AccountManagementRepository;
import com.odex.apps.account.db.repo.AccountRepository;
import com.odex.apps.account.db.repo.AccountingBatchAccountRepository;
import com.odex.apps.account.db.repo.AccountingBatchRepository;
import com.odex.apps.account.db.repo.AccountingReportManagementRepository;
import com.odex.apps.account.db.repo.AccountingReportRepository;
import com.odex.apps.account.db.repo.AnalyticMoveLineMngtRepository;
import com.odex.apps.account.db.repo.AnalyticMoveLineRepository;
import com.odex.apps.account.db.repo.ChequeRejectionManagementRepository;
import com.odex.apps.account.db.repo.ChequeRejectionRepository;
import com.odex.apps.account.db.repo.DebtRecoveryAccountRepository;
import com.odex.apps.account.db.repo.DebtRecoveryRepository;
import com.odex.apps.account.db.repo.DepositSlipAccountRepository;
import com.odex.apps.account.db.repo.DepositSlipRepository;
import com.odex.apps.account.db.repo.FiscalPositionManagementRepository;
import com.odex.apps.account.db.repo.FiscalPositionRepository;
import com.odex.apps.account.db.repo.FixedAssetDerogatoryLineManagementRepository;
import com.odex.apps.account.db.repo.FixedAssetDerogatoryLineRepository;
import com.odex.apps.account.db.repo.FixedAssetLineManagementRepository;
import com.odex.apps.account.db.repo.FixedAssetLineRepository;
import com.odex.apps.account.db.repo.FixedAssetManagementRepository;
import com.odex.apps.account.db.repo.FixedAssetRepository;
import com.odex.apps.account.db.repo.InvoiceBatchAccountRepository;
import com.odex.apps.account.db.repo.InvoiceBatchRepository;
import com.odex.apps.account.db.repo.InvoiceLineManagementRepository;
import com.odex.apps.account.db.repo.InvoiceLineRepository;
import com.odex.apps.account.db.repo.InvoiceManagementRepository;
import com.odex.apps.account.db.repo.InvoicePaymentManagementRepository;
import com.odex.apps.account.db.repo.InvoicePaymentRepository;
import com.odex.apps.account.db.repo.InvoiceRepository;
import com.odex.apps.account.db.repo.InvoiceTermAccountRepository;
import com.odex.apps.account.db.repo.InvoiceTermRepository;
import com.odex.apps.account.db.repo.JournalManagementRepository;
import com.odex.apps.account.db.repo.JournalRepository;
import com.odex.apps.account.db.repo.MoveLineManagementRepository;
import com.odex.apps.account.db.repo.MoveLineRepository;
import com.odex.apps.account.db.repo.MoveManagementRepository;
import com.odex.apps.account.db.repo.MoveRepository;
import com.odex.apps.account.db.repo.MoveTemplateManagementRepository;
import com.odex.apps.account.db.repo.MoveTemplateRepository;
import com.odex.apps.account.db.repo.PartnerAccountRepository;
import com.odex.apps.account.db.repo.PaymentScheduleAccountRepository;
import com.odex.apps.account.db.repo.PaymentScheduleRepository;
import com.odex.apps.account.db.repo.PaymentSessionAccountRepository;
import com.odex.apps.account.db.repo.PaymentSessionRepository;
import com.odex.apps.account.db.repo.PaymentVoucherManagementRepository;
import com.odex.apps.account.db.repo.PaymentVoucherRepository;
import com.odex.apps.account.db.repo.PeriodManagementRepository;
import com.odex.apps.account.db.repo.ReconcileGroupAccountRepository;
import com.odex.apps.account.db.repo.ReconcileGroupRepository;
import com.odex.apps.account.db.repo.ReconcileManagementRepository;
import com.odex.apps.account.db.repo.ReconcileRepository;
import com.odex.apps.account.db.repo.SubrogationReleaseManagementRepository;
import com.odex.apps.account.db.repo.SubrogationReleaseRepository;
import com.odex.apps.account.service.*;
import com.odex.apps.account.service.AccountManagementAttrsService;
import com.odex.apps.account.service.AccountManagementAttrsServiceImpl;
import com.odex.apps.account.service.accountingsituation.AccountingSituationAttrsService;
import com.odex.apps.account.service.accountingsituation.AccountingSituationAttrsServiceImpl;
import com.odex.apps.account.service.accountingsituation.AccountingSituationCheckService;
import com.odex.apps.account.service.accountingsituation.AccountingSituationCheckServiceImpl;
import com.odex.apps.account.service.accountingsituation.AccountingSituationGroupService;
import com.odex.apps.account.service.accountingsituation.AccountingSituationGroupServiceImpl;
import com.odex.apps.account.service.accountingsituation.AccountingSituationInitService;
import com.odex.apps.account.service.accountingsituation.AccountingSituationInitServiceImpl;
import com.odex.apps.account.service.accountingsituation.AccountingSituationRecordService;
import com.odex.apps.account.service.accountingsituation.AccountingSituationRecordServiceImpl;
import com.odex.apps.account.service.accountingsituation.AccountingSituationService;
import com.odex.apps.account.service.accountingsituation.AccountingSituationServiceImpl;
import com.odex.apps.account.service.analytic.AccountConfigAnalyticService;
import com.odex.apps.account.service.analytic.AccountConfigAnalyticServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticAccountService;
import com.odex.apps.account.service.analytic.AnalyticAccountServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticAttrsService;
import com.odex.apps.account.service.analytic.AnalyticAttrsServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticAxisByCompanyService;
import com.odex.apps.account.service.analytic.AnalyticAxisByCompanyServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticAxisFetchService;
import com.odex.apps.account.service.analytic.AnalyticAxisFetchServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticAxisService;
import com.odex.apps.account.service.analytic.AnalyticAxisServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticDistributionLineService;
import com.odex.apps.account.service.analytic.AnalyticDistributionLineServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticDistributionTemplateService;
import com.odex.apps.account.service.analytic.AnalyticDistributionTemplateServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticGroupService;
import com.odex.apps.account.service.analytic.AnalyticGroupServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticGroupingService;
import com.odex.apps.account.service.analytic.AnalyticGroupingServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticLineComputeService;
import com.odex.apps.account.service.analytic.AnalyticLineComputeServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticLineService;
import com.odex.apps.account.service.analytic.AnalyticLineServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticMoveLineComputeService;
import com.odex.apps.account.service.analytic.AnalyticMoveLineComputeServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticMoveLineGenerateRealService;
import com.odex.apps.account.service.analytic.AnalyticMoveLineGenerateRealServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticMoveLineParentService;
import com.odex.apps.account.service.analytic.AnalyticMoveLineParentServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticMoveLineQueryPercentageService;
import com.odex.apps.account.service.analytic.AnalyticMoveLineQueryPercentageServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticMoveLineQueryService;
import com.odex.apps.account.service.analytic.AnalyticMoveLineQueryServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticMoveLineService;
import com.odex.apps.account.service.analytic.AnalyticMoveLineServiceImpl;
import com.odex.apps.account.service.analytic.AnalyticToolService;
import com.odex.apps.account.service.analytic.AnalyticToolServiceImpl;
import com.odex.apps.account.service.analytic.ImportAnalyticInMoveService;
import com.odex.apps.account.service.analytic.ImportAnalyticInMoveServiceImpl;
import com.odex.apps.account.service.analytic.TradingNameAnalyticService;
import com.odex.apps.account.service.analytic.TradingNameAnalyticServiceImpl;
import com.odex.apps.account.service.app.AppAccountService;
import com.odex.apps.account.service.app.AppAccountServiceImpl;
import com.odex.apps.account.service.batch.AccountingBatchViewService;
import com.odex.apps.account.service.batch.AccountingBatchViewServiceImpl;
import com.odex.apps.account.service.batch.BatchPrintAccountingReportService;
import com.odex.apps.account.service.batch.BatchPrintAccountingReportServiceImpl;
import com.odex.apps.account.service.custom.AccountingReportValueCustomRuleService;
import com.odex.apps.account.service.custom.AccountingReportValueCustomRuleServiceImpl;
import com.odex.apps.account.service.custom.AccountingReportValueMoveLineService;
import com.odex.apps.account.service.custom.AccountingReportValueMoveLineServiceImpl;
import com.odex.apps.account.service.custom.AccountingReportValuePercentageService;
import com.odex.apps.account.service.custom.AccountingReportValuePercentageServiceImpl;
import com.odex.apps.account.service.custom.AccountingReportValueService;
import com.odex.apps.account.service.custom.AccountingReportValueServiceImpl;
import com.odex.apps.account.service.debtrecovery.DebtRecoveryHistoryService;
import com.odex.apps.account.service.debtrecovery.DebtRecoveryHistoryServiceImpl;
import com.odex.apps.account.service.extract.ExtractContextMoveService;
import com.odex.apps.account.service.extract.ExtractContextMoveServiceImpl;
import com.odex.apps.account.service.fecimport.FECImportService;
import com.odex.apps.account.service.fecimport.FECImportServiceImpl;
import com.odex.apps.account.service.fecimport.ImportFECTypeService;
import com.odex.apps.account.service.fecimport.ImportFECTypeServiceImpl;
import com.odex.apps.account.service.fecimport.ImportMoveFecService;
import com.odex.apps.account.service.fecimport.ImportMoveFecServiceImpl;
import com.odex.apps.account.service.fecimport.ImportMoveLineAmountService;
import com.odex.apps.account.service.fecimport.ImportMoveLineAmountServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetCategoryService;
import com.odex.apps.account.service.fixedasset.FixedAssetCategoryServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetDateService;
import com.odex.apps.account.service.fixedasset.FixedAssetDateServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetDerogatoryLineMoveService;
import com.odex.apps.account.service.fixedasset.FixedAssetDerogatoryLineMoveServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetDerogatoryLineService;
import com.odex.apps.account.service.fixedasset.FixedAssetDerogatoryLineServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetDisposalService;
import com.odex.apps.account.service.fixedasset.FixedAssetDisposalServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetFailOverControlService;
import com.odex.apps.account.service.fixedasset.FixedAssetFailOverControlServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetGenerationService;
import com.odex.apps.account.service.fixedasset.FixedAssetGenerationServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetGroupService;
import com.odex.apps.account.service.fixedasset.FixedAssetGroupServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetImportService;
import com.odex.apps.account.service.fixedasset.FixedAssetImportServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetLineComputationService;
import com.odex.apps.account.service.fixedasset.FixedAssetLineEconomicComputationServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetLineEconomicServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetLineGenerationService;
import com.odex.apps.account.service.fixedasset.FixedAssetLineGenerationServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetLineMoveService;
import com.odex.apps.account.service.fixedasset.FixedAssetLineMoveServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetLineService;
import com.odex.apps.account.service.fixedasset.FixedAssetLineToolService;
import com.odex.apps.account.service.fixedasset.FixedAssetLineToolServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetRecordService;
import com.odex.apps.account.service.fixedasset.FixedAssetRecordServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetService;
import com.odex.apps.account.service.fixedasset.FixedAssetServiceImpl;
import com.odex.apps.account.service.fixedasset.FixedAssetValidateService;
import com.odex.apps.account.service.fixedasset.FixedAssetValidateServiceImpl;
import com.odex.apps.account.service.fixedasset.attributes.FixedAssetAttrsService;
import com.odex.apps.account.service.fixedasset.attributes.FixedAssetAttrsServiceImpl;
import com.odex.apps.account.service.invoice.AdvancePaymentMoveLineCreateService;
import com.odex.apps.account.service.invoice.AdvancePaymentMoveLineCreateServiceImpl;
import com.odex.apps.account.service.invoice.AdvancePaymentRefundService;
import com.odex.apps.account.service.invoice.AdvancePaymentRefundServiceImpl;
import com.odex.apps.account.service.invoice.BankDetailsServiceAccount;
import com.odex.apps.account.service.invoice.InvoiceCategoryService;
import com.odex.apps.account.service.invoice.InvoiceCategoryServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceControlService;
import com.odex.apps.account.service.invoice.InvoiceControlServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceDomainService;
import com.odex.apps.account.service.invoice.InvoiceDomainServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceFinancialDiscountService;
import com.odex.apps.account.service.invoice.InvoiceFinancialDiscountServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceGlobalDiscountService;
import com.odex.apps.account.service.invoice.InvoiceGlobalDiscountServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceJournalService;
import com.odex.apps.account.service.invoice.InvoiceJournalServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceLineAnalyticService;
import com.odex.apps.account.service.invoice.InvoiceLineAnalyticServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceLineCheckService;
import com.odex.apps.account.service.invoice.InvoiceLineCheckServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceLineGroupService;
import com.odex.apps.account.service.invoice.InvoiceLineGroupServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceLineService;
import com.odex.apps.account.service.invoice.InvoiceLineServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceMergingService;
import com.odex.apps.account.service.invoice.InvoiceMergingServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceMergingViewService;
import com.odex.apps.account.service.invoice.InvoiceMergingViewServiceImpl;
import com.odex.apps.account.service.invoice.InvoicePfpValidateService;
import com.odex.apps.account.service.invoice.InvoicePfpValidateServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceService;
import com.odex.apps.account.service.invoice.InvoiceServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermDateComputeService;
import com.odex.apps.account.service.invoice.InvoiceTermDateComputeServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermFilterService;
import com.odex.apps.account.service.invoice.InvoiceTermFilterServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermFinancialDiscountService;
import com.odex.apps.account.service.invoice.InvoiceTermFinancialDiscountServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermPaymentGroupService;
import com.odex.apps.account.service.invoice.InvoiceTermPaymentGroupServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermPfpService;
import com.odex.apps.account.service.invoice.InvoiceTermPfpServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermPfpToolService;
import com.odex.apps.account.service.invoice.InvoiceTermPfpToolServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermPfpUpdateService;
import com.odex.apps.account.service.invoice.InvoiceTermPfpUpdateServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermPfpValidateService;
import com.odex.apps.account.service.invoice.InvoiceTermPfpValidateServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermPfpValidatorSyncService;
import com.odex.apps.account.service.invoice.InvoiceTermPfpValidatorSyncServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermReplaceService;
import com.odex.apps.account.service.invoice.InvoiceTermReplaceServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermService;
import com.odex.apps.account.service.invoice.InvoiceTermServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceTermToolService;
import com.odex.apps.account.service.invoice.InvoiceTermToolServiceImpl;
import com.odex.apps.account.service.invoice.InvoiceVatLiabilityService;
import com.odex.apps.account.service.invoice.InvoiceVatLiabilityServiceImpl;
import com.odex.apps.account.service.invoice.LatePaymentInterestInvoiceService;
import com.odex.apps.account.service.invoice.LatePaymentInterestInvoiceServiceImpl;
import com.odex.apps.account.service.invoice.attributes.InvoiceLineAttrsService;
import com.odex.apps.account.service.invoice.attributes.InvoiceLineAttrsServiceImpl;
import com.odex.apps.account.service.invoice.attributes.InvoiceLineTaxAttrsService;
import com.odex.apps.account.service.invoice.attributes.InvoiceLineTaxAttrsServiceImpl;
import com.odex.apps.account.service.invoice.attributes.InvoiceTermPaymentAttrsService;
import com.odex.apps.account.service.invoice.attributes.InvoiceTermPaymentAttrsServiceImpl;
import com.odex.apps.account.service.invoice.print.InvoicePrintService;
import com.odex.apps.account.service.invoice.print.InvoicePrintServiceImpl;
import com.odex.apps.account.service.invoice.tax.InvoiceLineTaxGroupService;
import com.odex.apps.account.service.invoice.tax.InvoiceLineTaxGroupServiceImpl;
import com.odex.apps.account.service.invoice.tax.InvoiceLineTaxRecordService;
import com.odex.apps.account.service.invoice.tax.InvoiceLineTaxRecordServiceImpl;
import com.odex.apps.account.service.invoice.tax.InvoiceLineTaxToolService;
import com.odex.apps.account.service.invoice.tax.InvoiceLineTaxToolServiceImpl;
import com.odex.apps.account.service.invoice.tax.InvoiceTaxComputeService;
import com.odex.apps.account.service.invoice.tax.InvoiceTaxComputeServiceImpl;
import com.odex.apps.account.service.invoice.workflow.cancel.WorkflowCancelService;
import com.odex.apps.account.service.invoice.workflow.cancel.WorkflowCancelServiceImpl;
import com.odex.apps.account.service.invoice.workflow.validate.WorkflowValidationService;
import com.odex.apps.account.service.invoice.workflow.validate.WorkflowValidationServiceImpl;
import com.odex.apps.account.service.invoice.workflow.ventilate.WorkflowVentilationService;
import com.odex.apps.account.service.invoice.workflow.ventilate.WorkflowVentilationServiceImpl;
import com.odex.apps.account.service.invoiceterm.InvoiceTermAttrsService;
import com.odex.apps.account.service.invoiceterm.InvoiceTermAttrsServiceImpl;
import com.odex.apps.account.service.invoiceterm.InvoiceTermGroupService;
import com.odex.apps.account.service.invoiceterm.InvoiceTermGroupServiceImpl;
import com.odex.apps.account.service.invoiceterm.InvoiceTermRecordService;
import com.odex.apps.account.service.invoiceterm.InvoiceTermRecordServiceImpl;
import com.odex.apps.account.service.journal.JournalCheckPartnerTypeService;
import com.odex.apps.account.service.journal.JournalCheckPartnerTypeServiceImpl;
import com.odex.apps.account.service.journal.JournalControlService;
import com.odex.apps.account.service.journal.JournalControlServiceImpl;
import com.odex.apps.account.service.move.MoveControlService;
import com.odex.apps.account.service.move.MoveControlServiceImpl;
import com.odex.apps.account.service.move.MoveCounterPartService;
import com.odex.apps.account.service.move.MoveCounterPartServiceImpl;
import com.odex.apps.account.service.move.MoveCreateFromInvoiceService;
import com.odex.apps.account.service.move.MoveCreateFromInvoiceServiceImpl;
import com.odex.apps.account.service.move.MoveCreateService;
import com.odex.apps.account.service.move.MoveCreateServiceImpl;
import com.odex.apps.account.service.move.MoveCutOffService;
import com.odex.apps.account.service.move.MoveCutOffServiceImpl;
import com.odex.apps.account.service.move.MoveInvoiceTermService;
import com.odex.apps.account.service.move.MoveInvoiceTermServiceImpl;
import com.odex.apps.account.service.move.MoveLineControlService;
import com.odex.apps.account.service.move.MoveLineControlServiceImpl;
import com.odex.apps.account.service.move.MoveLineInvoiceTermService;
import com.odex.apps.account.service.move.MoveLineInvoiceTermServiceImpl;
import com.odex.apps.account.service.move.MoveLoadDefaultConfigService;
import com.odex.apps.account.service.move.MoveLoadDefaultConfigServiceImpl;
import com.odex.apps.account.service.move.MovePfpService;
import com.odex.apps.account.service.move.MovePfpServiceImpl;
import com.odex.apps.account.service.move.MovePfpToolService;
import com.odex.apps.account.service.move.MovePfpToolServiceImpl;
import com.odex.apps.account.service.move.MovePfpValidateService;
import com.odex.apps.account.service.move.MovePfpValidateServiceImpl;
import com.odex.apps.account.service.move.MoveRemoveService;
import com.odex.apps.account.service.move.MoveRemoveServiceImpl;
import com.odex.apps.account.service.move.MoveReverseService;
import com.odex.apps.account.service.move.MoveReverseServiceImpl;
import com.odex.apps.account.service.move.MoveSimulateService;
import com.odex.apps.account.service.move.MoveSimulateServiceImpl;
import com.odex.apps.account.service.move.MoveToolService;
import com.odex.apps.account.service.move.MoveToolServiceImpl;
import com.odex.apps.account.service.move.MoveValidateService;
import com.odex.apps.account.service.move.MoveValidateServiceImpl;
import com.odex.apps.account.service.move.MoveViewHelperService;
import com.odex.apps.account.service.move.MoveViewHelperServiceImpl;
import com.odex.apps.account.service.move.PaymentMoveLineDistributionService;
import com.odex.apps.account.service.move.PaymentMoveLineDistributionServiceImpl;
import com.odex.apps.account.service.move.SimulatedMoveService;
import com.odex.apps.account.service.move.SimulatedMoveServiceImpl;
import com.odex.apps.account.service.move.attributes.MoveAttrsService;
import com.odex.apps.account.service.move.attributes.MoveAttrsServiceImpl;
import com.odex.apps.account.service.move.control.MoveCheckService;
import com.odex.apps.account.service.move.control.MoveCheckServiceImpl;
import com.odex.apps.account.service.move.massentry.MassEntryCheckService;
import com.odex.apps.account.service.move.massentry.MassEntryCheckServiceImpl;
import com.odex.apps.account.service.move.massentry.MassEntryMoveCreateService;
import com.odex.apps.account.service.move.massentry.MassEntryMoveCreateServiceImpl;
import com.odex.apps.account.service.move.massentry.MassEntryMoveUpdateService;
import com.odex.apps.account.service.move.massentry.MassEntryMoveUpdateServiceImpl;
import com.odex.apps.account.service.move.massentry.MassEntryMoveValidateService;
import com.odex.apps.account.service.move.massentry.MassEntryMoveValidateServiceImpl;
import com.odex.apps.account.service.move.massentry.MassEntryService;
import com.odex.apps.account.service.move.massentry.MassEntryServiceImpl;
import com.odex.apps.account.service.move.massentry.MassEntryToolService;
import com.odex.apps.account.service.move.massentry.MassEntryToolServiceImpl;
import com.odex.apps.account.service.move.massentry.MassEntryVerificationService;
import com.odex.apps.account.service.move.massentry.MassEntryVerificationServiceImpl;
import com.odex.apps.account.service.move.record.MoveDefaultService;
import com.odex.apps.account.service.move.record.MoveDefaultServiceImpl;
import com.odex.apps.account.service.move.record.MoveGroupOnChangeService;
import com.odex.apps.account.service.move.record.MoveGroupOnChangeServiceImpl;
import com.odex.apps.account.service.move.record.MoveGroupService;
import com.odex.apps.account.service.move.record.MoveGroupServiceImpl;
import com.odex.apps.account.service.move.record.MoveRecordSetService;
import com.odex.apps.account.service.move.record.MoveRecordSetServiceImpl;
import com.odex.apps.account.service.move.record.MoveRecordUpdateService;
import com.odex.apps.account.service.move.record.MoveRecordUpdateServiceImpl;
import com.odex.apps.account.service.move.template.MoveTemplateCheckService;
import com.odex.apps.account.service.move.template.MoveTemplateCheckServiceImpl;
import com.odex.apps.account.service.move.template.MoveTemplateGroupService;
import com.odex.apps.account.service.move.template.MoveTemplateGroupServiceImpl;
import com.odex.apps.account.service.move.template.MoveTemplateLineAnalyticService;
import com.odex.apps.account.service.move.template.MoveTemplateLineAnalyticServiceImpl;
import com.odex.apps.account.service.move.template.MoveTemplateLineComputeAnalyticService;
import com.odex.apps.account.service.move.template.MoveTemplateLineComputeAnalyticServiceImpl;
import com.odex.apps.account.service.move.template.MoveTemplateService;
import com.odex.apps.account.service.move.template.MoveTemplateServiceImpl;
import com.odex.apps.account.service.move.template.MoveTemplateTaxService;
import com.odex.apps.account.service.move.template.MoveTemplateTaxServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineAttrsService;
import com.odex.apps.account.service.moveline.MoveLineAttrsServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineCheckService;
import com.odex.apps.account.service.moveline.MoveLineCheckServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineComputeAnalyticService;
import com.odex.apps.account.service.moveline.MoveLineComputeAnalyticServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineConsolidateService;
import com.odex.apps.account.service.moveline.MoveLineConsolidateServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineCreateService;
import com.odex.apps.account.service.moveline.MoveLineCreateServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineCurrencyService;
import com.odex.apps.account.service.moveline.MoveLineCurrencyServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineDefaultService;
import com.odex.apps.account.service.moveline.MoveLineDefaultServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineFinancialDiscountService;
import com.odex.apps.account.service.moveline.MoveLineFinancialDiscountServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineGroupService;
import com.odex.apps.account.service.moveline.MoveLineGroupServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineRecordService;
import com.odex.apps.account.service.moveline.MoveLineRecordServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineService;
import com.odex.apps.account.service.moveline.MoveLineServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineTaxService;
import com.odex.apps.account.service.moveline.MoveLineTaxServiceImpl;
import com.odex.apps.account.service.moveline.MoveLineToolService;
import com.odex.apps.account.service.moveline.MoveLineToolServiceImpl;
import com.odex.apps.account.service.moveline.massentry.MoveLineMassEntryAttrsService;
import com.odex.apps.account.service.moveline.massentry.MoveLineMassEntryAttrsServiceImpl;
import com.odex.apps.account.service.moveline.massentry.MoveLineMassEntryGroupService;
import com.odex.apps.account.service.moveline.massentry.MoveLineMassEntryGroupServiceImpl;
import com.odex.apps.account.service.moveline.massentry.MoveLineMassEntryRecordService;
import com.odex.apps.account.service.moveline.massentry.MoveLineMassEntryRecordServiceImpl;
import com.odex.apps.account.service.moveline.massentry.MoveLineMassEntryService;
import com.odex.apps.account.service.moveline.massentry.MoveLineMassEntryServiceImpl;
import com.odex.apps.account.service.note.InvoiceNoteService;
import com.odex.apps.account.service.note.InvoiceNoteServiceImpl;
import com.odex.apps.account.service.notebills.NoteBillsCreateService;
import com.odex.apps.account.service.notebills.NoteBillsCreateServiceImpl;
import com.odex.apps.account.service.payment.PaymentModeInitService;
import com.odex.apps.account.service.payment.PaymentModeInitServiceImpl;
import com.odex.apps.account.service.payment.PaymentModeInterestRateService;
import com.odex.apps.account.service.payment.PaymentModeInterestRateServiceImpl;
import com.odex.apps.account.service.payment.PaymentModeService;
import com.odex.apps.account.service.payment.PaymentModeServiceImpl;
import com.odex.apps.account.service.payment.PaymentService;
import com.odex.apps.account.service.payment.PaymentServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentAlertService;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentAlertServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentCancelService;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentCancelServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentComputeService;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentComputeServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentCreateService;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentCreateServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentFinancialDiscountService;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentFinancialDiscountServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentMoveCreateService;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentMoveCreateServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentToolService;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentToolServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentValidateService;
import com.odex.apps.account.service.payment.invoice.payment.InvoicePaymentValidateServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoiceTermPaymentService;
import com.odex.apps.account.service.payment.invoice.payment.InvoiceTermPaymentServiceImpl;
import com.odex.apps.account.service.payment.invoice.payment.InvoiceTermPaymentToolService;
import com.odex.apps.account.service.payment.invoice.payment.InvoiceTermPaymentToolServiceImpl;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionBillOfExchangeValidateService;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionBillOfExchangeValidateServiceImpl;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionCancelService;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionCancelServiceImpl;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionEmailService;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionEmailServiceImpl;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionService;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionServiceImpl;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionValidateService;
import com.odex.apps.account.service.payment.paymentsession.PaymentSessionValidateServiceImpl;
import com.odex.apps.account.service.payment.paymentvoucher.PayVoucherDueElementService;
import com.odex.apps.account.service.payment.paymentvoucher.PayVoucherDueElementServiceImpl;
import com.odex.apps.account.service.payment.paymentvoucher.PaymentVoucherCancelService;
import com.odex.apps.account.service.payment.paymentvoucher.PaymentVoucherCancelServiceImpl;
import com.odex.apps.account.service.period.PeriodCheckService;
import com.odex.apps.account.service.period.PeriodCheckServiceImpl;
import com.odex.apps.account.service.period.PeriodControlService;
import com.odex.apps.account.service.period.PeriodControlServiceImpl;
import com.odex.apps.account.service.period.PeriodServiceAccount;
import com.odex.apps.account.service.period.PeriodServiceAccountImpl;
import com.odex.apps.account.service.reconcile.ReconcileCheckService;
import com.odex.apps.account.service.reconcile.ReconcileCheckServiceImpl;
import com.odex.apps.account.service.reconcile.ReconcileInvoiceTermComputationService;
import com.odex.apps.account.service.reconcile.ReconcileInvoiceTermComputationServiceImpl;
import com.odex.apps.account.service.reconcile.ReconcileService;
import com.odex.apps.account.service.reconcile.ReconcileServiceImpl;
import com.odex.apps.account.service.reconcile.ReconcileToolService;
import com.odex.apps.account.service.reconcile.ReconcileToolServiceImpl;
import com.odex.apps.account.service.reconcile.UnreconcileService;
import com.odex.apps.account.service.reconcile.UnreconcileServiceImpl;
import com.odex.apps.account.service.reconcile.foreignexchange.ForeignExchangeGapService;
import com.odex.apps.account.service.reconcile.foreignexchange.ForeignExchangeGapServiceImpl;
import com.odex.apps.account.service.reconcile.foreignexchange.ForeignExchangeGapToolService;
import com.odex.apps.account.service.reconcile.foreignexchange.ForeignExchangeGapToolServiceImpl;
import com.odex.apps.account.service.reconcile.reconcilegroup.ReconcileGroupService;
import com.odex.apps.account.service.reconcile.reconcilegroup.ReconcileGroupServiceImpl;
import com.odex.apps.account.service.reconcile.reconcilegroup.ReconcileGroupToolService;
import com.odex.apps.account.service.reconcile.reconcilegroup.ReconcileGroupToolServiceImpl;
import com.odex.apps.account.service.reconcile.reconcilegroup.UnreconcileGroupService;
import com.odex.apps.account.service.reconcile.reconcilegroup.UnreconcileGroupServiceImpl;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupFetchService;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupFetchServiceImpl;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupLetterService;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupLetterServiceImpl;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupProposalService;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupProposalServiceImpl;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupSequenceService;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupSequenceServiceImpl;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupUnletterService;
import com.odex.apps.account.service.reconcilegroup.ReconcileGroupUnletterServiceImpl;
import com.odex.apps.account.service.umr.UmrNumberService;
import com.odex.apps.account.service.umr.UmrNumberServiceImpl;
import com.odex.apps.account.service.umr.UmrService;
import com.odex.apps.account.service.umr.UmrServiceImpl;
import com.odex.apps.account.util.TaxAccountToolService;
import com.odex.apps.account.util.TaxAccountToolServiceImpl;
import com.odex.apps.base.db.repo.PartnerAddressRepository;
import com.odex.apps.base.db.repo.PartnerBaseRepository;
import com.odex.apps.base.db.repo.PeriodRepository;
import com.odex.apps.base.service.BankDetailsServiceImpl;
import com.odex.apps.base.service.PeriodServiceImpl;
import com.odex.apps.base.service.tax.AccountManagementServiceImpl;
import com.odex.apps.base.service.tax.FiscalPositionServiceImpl;
import com.axelor.message.service.TemplateMessageService;
import com.axelor.message.service.TemplateMessageServiceImpl;

public class AccountModule extends AxelorModule {

  @Override
  protected void configure() {
    bind(AddressServiceAccountImpl.class);

    bind(AccountManagementAttrsService.class).to(AccountManagementAttrsServiceImpl.class);

    bind(AccountManagementCheckService.class).to(AccountManagementCheckServiceImpl.class);

    bind(AccountManagementServiceImpl.class).to(AccountManagementServiceAccountImpl.class);

    bind(AccountManagementAccountService.class).to(AccountManagementServiceAccountImpl.class);

    bind(FiscalPositionServiceImpl.class).to(FiscalPositionAccountServiceImpl.class);

    bind(FiscalPositionAccountService.class).to(FiscalPositionAccountServiceImpl.class);

    bind(TemplateMessageService.class).to(TemplateMessageServiceImpl.class);

    bind(InvoiceRepository.class).to(InvoiceManagementRepository.class);

    bind(MoveRepository.class).to(MoveManagementRepository.class);

    bind(MoveLineRepository.class).to(MoveLineManagementRepository.class);

    bind(AccountingReportRepository.class).to(AccountingReportManagementRepository.class);

    bind(AccountingReportService.class).to(AccountingReportServiceImpl.class);

    bind(AccountingReportDas2Service.class).to(AccountingReportDas2ServiceImpl.class);

    bind(AccountingReportDas2CheckService.class).to(AccountingReportDas2CheckServiceImpl.class);

    bind(AccountingReportPrintService.class).to(AccountingReportPrintServiceImpl.class);

    bind(AccountingReportToolService.class).to(AccountingReportToolServiceImpl.class);

    bind(AccountingReportTypeService.class).to(AccountingReportTypeServiceImpl.class);

    bind(JournalRepository.class).to(JournalManagementRepository.class);

    bind(PaymentVoucherRepository.class).to(PaymentVoucherManagementRepository.class);

    bind(InvoiceService.class).to(InvoiceServiceImpl.class);

    bind(InvoiceVatLiabilityService.class).to(InvoiceVatLiabilityServiceImpl.class);

    bind(InvoicePrintService.class).to(InvoicePrintServiceImpl.class);

    bind(PartnerBaseRepository.class).to(PartnerAccountRepository.class);

    bind(AnalyticMoveLineService.class).to(AnalyticMoveLineServiceImpl.class);

    bind(AnalyticMoveLineComputeService.class).to(AnalyticMoveLineComputeServiceImpl.class);

    bind(InvoicePaymentRepository.class).to(InvoicePaymentManagementRepository.class);

    bind(InvoicePaymentValidateService.class).to(InvoicePaymentValidateServiceImpl.class);

    bind(InvoicePaymentCreateService.class).to(InvoicePaymentCreateServiceImpl.class);

    bind(InvoicePaymentCancelService.class).to(InvoicePaymentCancelServiceImpl.class);

    bind(InvoicePaymentToolService.class).to(InvoicePaymentToolServiceImpl.class);

    bind(AnalyticMoveLineRepository.class).to(AnalyticMoveLineMngtRepository.class);

    bind(ReconcileService.class).to(ReconcileServiceImpl.class);

    bind(ReconcileRepository.class).to(ReconcileManagementRepository.class);

    bind(AppAccountService.class).to(AppAccountServiceImpl.class);

    bind(AccountingSituationService.class).to(AccountingSituationServiceImpl.class);

    bind(AccountingSituationGroupService.class).to(AccountingSituationGroupServiceImpl.class);

    bind(AccountingSituationRecordService.class).to(AccountingSituationRecordServiceImpl.class);

    bind(AccountingSituationAttrsService.class).to(AccountingSituationAttrsServiceImpl.class);

    bind(AccountingSituationCheckService.class).to(AccountingSituationCheckServiceImpl.class);

    bind(PaymentModeInitService.class).to(PaymentModeInitServiceImpl.class);

    bind(PaymentModeService.class).to(PaymentModeServiceImpl.class);

    bind(BankDetailsServiceImpl.class).to(BankDetailsServiceAccountImpl.class);

    bind(MoveLineExportService.class).to(MoveLineExportServiceImpl.class);

    bind(AccountingBatchRepository.class).to(AccountingBatchAccountRepository.class);

    bind(InvoiceBatchRepository.class).to(InvoiceBatchAccountRepository.class);

    bind(AccountRepository.class).to(AccountAccountRepository.class);

    bind(WorkflowVentilationService.class).to(WorkflowVentilationServiceImpl.class);

    bind(WorkflowCancelService.class).to(WorkflowCancelServiceImpl.class);

    bind(WorkflowValidationService.class).to(WorkflowValidationServiceImpl.class);

    bind(SubrogationReleaseService.class).to(SubrogationReleaseServiceImpl.class);

    bind(NotificationService.class).to(NotificationServiceImpl.class);

    bind(PaymentScheduleService.class).to(PaymentScheduleServiceImpl.class);

    bind(PaymentScheduleLineService.class).to(PaymentScheduleLineServiceImpl.class);

    bind(DepositSlipRepository.class).to(DepositSlipAccountRepository.class);

    bind(DepositSlipService.class).to(DepositSlipServiceImpl.class);

    bind(InvoiceLineService.class).to(InvoiceLineServiceImpl.class);

    bind(InvoiceLineAnalyticService.class).to(InvoiceLineAnalyticServiceImpl.class);

    bind(TemplateMessageAccountService.class).to(TemplateMessageAccountServiceImpl.class);

    PartnerAddressRepository.modelPartnerFieldMap.put(Invoice.class.getName(), "partner");

    bind(UmrNumberService.class).to(UmrNumberServiceImpl.class);

    bind(ReconcileGroupSequenceService.class).to(ReconcileGroupSequenceServiceImpl.class);

    bind(ReconcileGroupRepository.class).to(ReconcileGroupAccountRepository.class);

    bind(ReconcileGroupService.class).to(ReconcileGroupServiceImpl.class);

    bind(ReconcileGroupFetchService.class).to(ReconcileGroupFetchServiceImpl.class);

    bind(ReconcileGroupLetterService.class).to(ReconcileGroupLetterServiceImpl.class);

    bind(ReconcileGroupUnletterService.class).to(ReconcileGroupUnletterServiceImpl.class);

    bind(ReconcileGroupProposalService.class).to(ReconcileGroupProposalServiceImpl.class);

    bind(SubrogationReleaseRepository.class).to(SubrogationReleaseManagementRepository.class);

    bind(PeriodServiceImpl.class).to(PeriodServiceAccountImpl.class);

    bind(FixedAssetRepository.class).to(FixedAssetManagementRepository.class);

    bind(FixedAssetService.class).to(FixedAssetServiceImpl.class);

    bind(FixedAssetDisposalService.class).to(FixedAssetDisposalServiceImpl.class);

    bind(FixedAssetLineMoveService.class).to(FixedAssetLineMoveServiceImpl.class);

    bind(FixedAssetLineComputationService.class)
        .to(FixedAssetLineEconomicComputationServiceImpl.class);

    bind(ExtractContextMoveService.class).to(ExtractContextMoveServiceImpl.class);

    bind(AccountingCloseAnnualService.class).to(AccountingCloseAnnualServiceImpl.class);

    bind(PeriodServiceAccount.class).to(PeriodServiceAccountImpl.class);

    bind(TaxPaymentMoveLineService.class).to(TaxPaymentMoveLineServiceImpl.class);

    bind(PaymentService.class).to(PaymentServiceImpl.class);

    bind(MoveLineService.class).to(MoveLineServiceImpl.class);

    bind(MoveLineControlService.class).to(MoveLineControlServiceImpl.class);

    bind(DebtRecoveryRepository.class).to(DebtRecoveryAccountRepository.class);

    bind(DebtRecoveryHistoryService.class).to(DebtRecoveryHistoryServiceImpl.class);

    bind(InvoiceTermService.class).to(InvoiceTermServiceImpl.class);

    bind(InvoiceTermPaymentService.class).to(InvoiceTermPaymentServiceImpl.class);

    bind(AnalyticDistributionTemplateService.class)
        .to(AnalyticDistributionTemplateServiceImpl.class);

    bind(AnalyticAxisService.class).to(AnalyticAxisServiceImpl.class);

    bind(AnalyticGroupingService.class).to(AnalyticGroupingServiceImpl.class);

    bind(AnalyticAxisFetchService.class).to(AnalyticAxisFetchServiceImpl.class);

    bind(AnalyticAxisByCompanyService.class).to(AnalyticAxisByCompanyServiceImpl.class);

    bind(AccountingReportMoveLineService.class).to(AccountingReportMoveLineServiceImpl.class);

    bind(PaymentMoveLineDistributionService.class).to(PaymentMoveLineDistributionServiceImpl.class);

    bind(MoveCreateService.class).to(MoveCreateServiceImpl.class);

    bind(FixedAssetDerogatoryLineService.class).to(FixedAssetDerogatoryLineServiceImpl.class);

    bind(FixedAssetDerogatoryLineMoveService.class)
        .to(FixedAssetDerogatoryLineMoveServiceImpl.class);

    bind(FixedAssetLineService.class).to(FixedAssetLineEconomicServiceImpl.class);

    bind(FixedAssetFailOverControlService.class).to(FixedAssetFailOverControlServiceImpl.class);

    bind(FixedAssetGenerationService.class).to(FixedAssetGenerationServiceImpl.class);

    bind(MoveCutOffService.class).to(MoveCutOffServiceImpl.class);

    bind(MoveCounterPartService.class).to(MoveCounterPartServiceImpl.class);

    bind(MoveCreateFromInvoiceService.class).to(MoveCreateFromInvoiceServiceImpl.class);

    bind(MoveLoadDefaultConfigService.class).to(MoveLoadDefaultConfigServiceImpl.class);

    bind(MoveReverseService.class).to(MoveReverseServiceImpl.class);

    bind(MoveViewHelperService.class).to(MoveViewHelperServiceImpl.class);

    bind(MoveToolService.class).to(MoveToolServiceImpl.class);

    bind(MoveLineComputeAnalyticService.class).to(MoveLineComputeAnalyticServiceImpl.class);

    bind(MoveLineConsolidateService.class).to(MoveLineConsolidateServiceImpl.class);

    bind(MoveLineCreateService.class).to(MoveLineCreateServiceImpl.class);

    bind(MoveLineToolService.class).to(MoveLineToolServiceImpl.class);

    bind(MoveLineTaxService.class).to(MoveLineTaxServiceImpl.class);

    bind(MoveValidateService.class).to(MoveValidateServiceImpl.class);

    bind(MoveSimulateService.class).to(MoveSimulateServiceImpl.class);

    bind(MoveRemoveService.class).to(MoveRemoveServiceImpl.class);

    bind(AnalyticDistributionLineService.class).to(AnalyticDistributionLineServiceImpl.class);

    bind(AccountManagementRepository.class).to(AccountManagementAccountRepository.class);

    bind(PaymentSessionRepository.class).to(PaymentSessionAccountRepository.class);

    bind(FECImportService.class).to(FECImportServiceImpl.class);

    bind(AccountingSituationInitService.class).to(AccountingSituationInitServiceImpl.class);

    bind(InvoiceMergingService.class).to(InvoiceMergingServiceImpl.class);

    bind(InvoiceMergingViewService.class).to(InvoiceMergingViewServiceImpl.class);

    bind(JournalControlService.class).to(JournalControlServiceImpl.class);

    bind(JournalCheckPartnerTypeService.class).to(JournalCheckPartnerTypeServiceImpl.class);

    bind(FixedAssetCategoryService.class).to(FixedAssetCategoryServiceImpl.class);

    bind(AnalyticAxisControlService.class).to(AnalyticAxisControlServiceImpl.class);

    bind(InvoiceControlService.class).to(InvoiceControlServiceImpl.class);

    bind(AnalyticJournalControlService.class).to(AnalyticJournalControlServiceImpl.class);

    bind(PeriodRepository.class).to(PeriodManagementRepository.class);

    bind(PeriodControlService.class).to(PeriodControlServiceImpl.class);

    bind(YearControlService.class).to(YearControlServiceImpl.class);

    bind(PaymentModeControlService.class).to(PaymentModeControlServiceImpl.class);

    bind(AnalyticToolService.class).to(AnalyticToolServiceImpl.class);

    bind(AnalyticAccountService.class).to(AnalyticAccountServiceImpl.class);

    bind(TaxAccountToolService.class).to(TaxAccountToolServiceImpl.class);

    bind(ClosureAssistantLineService.class).to(ClosureAssistantLineServiceImpl.class);

    bind(NoteBillsCreateService.class).to(NoteBillsCreateServiceImpl.class);

    bind(AccountConfigAnalyticService.class).to(AccountConfigAnalyticServiceImpl.class);

    bind(BatchPrintAccountingReportService.class).to(BatchPrintAccountingReportServiceImpl.class);

    bind(InvoiceTermRepository.class).to(InvoiceTermAccountRepository.class);

    bind(InvoiceVisibilityService.class).to(InvoiceVisibilityServiceImpl.class);

    bind(PaymentSessionService.class).to(PaymentSessionServiceImpl.class);

    bind(PaymentSessionValidateService.class).to(PaymentSessionValidateServiceImpl.class);

    bind(PaymentSessionBillOfExchangeValidateService.class)
        .to(PaymentSessionBillOfExchangeValidateServiceImpl.class);

    bind(PaymentSessionCancelService.class).to(PaymentSessionCancelServiceImpl.class);

    bind(PaymentSessionEmailService.class).to(PaymentSessionEmailServiceImpl.class);

    bind(PayVoucherDueElementService.class).to(PayVoucherDueElementServiceImpl.class);

    bind(MoveInvoiceTermService.class).to(MoveInvoiceTermServiceImpl.class);

    bind(MoveLineInvoiceTermService.class).to(MoveLineInvoiceTermServiceImpl.class);

    bind(ClosureAssistantService.class).to(ClosureAssistantServiceImpl.class);

    bind(AnalyticLineService.class).to(AnalyticLineServiceImpl.class);

    bind(FixedAssetDateService.class).to(FixedAssetDateServiceImpl.class);

    bind(SimulatedMoveService.class).to(SimulatedMoveServiceImpl.class);

    bind(FixedAssetValidateService.class).to(FixedAssetValidateServiceImpl.class);

    bind(AnalyticMoveLineQueryService.class).to(AnalyticMoveLineQueryServiceImpl.class);

    bind(AnalyticMoveLineQueryPercentageService.class)
        .to(AnalyticMoveLineQueryPercentageServiceImpl.class);

    bind(InvoiceTermPfpService.class).to(InvoiceTermPfpServiceImpl.class);

    bind(InvoiceTermPfpValidatorSyncService.class).to(InvoiceTermPfpValidatorSyncServiceImpl.class);

    bind(AccountingCutOffService.class).to(AccountingCutOffServiceImpl.class);

    bind(FixedAssetLineToolService.class).to(FixedAssetLineToolServiceImpl.class);

    bind(InvoiceDomainService.class).to(InvoiceDomainServiceImpl.class);

    bind(AnalyticMoveLineGenerateRealService.class)
        .to(AnalyticMoveLineGenerateRealServiceImpl.class);

    bind(SubrogationReleaseWorkflowService.class).to(SubrogationReleaseWorkflowServiceImpl.class);

    bind(ChequeRejectionRepository.class).to(ChequeRejectionManagementRepository.class);

    bind(MoveControlService.class).to(MoveControlServiceImpl.class);

    bind(PaymentVoucherCancelService.class).to(PaymentVoucherCancelServiceImpl.class);

    bind(MoveLineQueryService.class).to(MoveLineQueryServiceImpl.class);

    bind(InvoiceFinancialDiscountService.class).to(InvoiceFinancialDiscountServiceImpl.class);

    bind(AccountingReportValueService.class).to(AccountingReportValueServiceImpl.class);

    bind(AccountingReportValueCustomRuleService.class)
        .to(AccountingReportValueCustomRuleServiceImpl.class);

    bind(AccountingReportValueMoveLineService.class)
        .to(AccountingReportValueMoveLineServiceImpl.class);

    bind(AccountingReportValuePercentageService.class)
        .to(AccountingReportValuePercentageServiceImpl.class);

    bind(AccountEquivService.class).to(AccountEquivServiceImpl.class);

    bind(FixedAssetImportService.class).to(FixedAssetImportServiceImpl.class);

    bind(FixedAssetLineGenerationService.class).to(FixedAssetLineGenerationServiceImpl.class);

    bind(AccountCustomerService.class).to(AccountCustomerServiceImpl.class);

    bind(MoveLineAttrsService.class).to(MoveLineAttrsServiceImpl.class);

    bind(MoveLineCheckService.class).to(MoveLineCheckServiceImpl.class);

    bind(MoveLineDefaultService.class).to(MoveLineDefaultServiceImpl.class);

    bind(MoveLineGroupService.class).to(MoveLineGroupServiceImpl.class);

    bind(MoveLineRecordService.class).to(MoveLineRecordServiceImpl.class);

    bind(MoveLineCurrencyService.class).to(MoveLineCurrencyServiceImpl.class);

    bind(MoveDefaultService.class).to(MoveDefaultServiceImpl.class);

    bind(MoveGroupService.class).to(MoveGroupServiceImpl.class);

    bind(MoveCheckService.class).to(MoveCheckServiceImpl.class);

    bind(MoveAttrsService.class).to(MoveAttrsServiceImpl.class);

    bind(MoveLineCheckService.class).to(MoveLineCheckServiceImpl.class);

    bind(MoveRecordUpdateService.class).to(MoveRecordUpdateServiceImpl.class);

    bind(MoveRecordSetService.class).to(MoveRecordSetServiceImpl.class);

    bind(MovePfpService.class).to(MovePfpServiceImpl.class);

    bind(MassEntryService.class).to(MassEntryServiceImpl.class);

    bind(MassEntryToolService.class).to(MassEntryToolServiceImpl.class);

    bind(MassEntryVerificationService.class).to(MassEntryVerificationServiceImpl.class);

    bind(MassEntryMoveCreateService.class).to(MassEntryMoveCreateServiceImpl.class);

    bind(MoveLineMassEntryService.class).to(MoveLineMassEntryServiceImpl.class);

    bind(MoveLineMassEntryGroupService.class).to(MoveLineMassEntryGroupServiceImpl.class);

    bind(MoveLineMassEntryAttrsService.class).to(MoveLineMassEntryAttrsServiceImpl.class);

    bind(MoveLineMassEntryRecordService.class).to(MoveLineMassEntryRecordServiceImpl.class);

    bind(PaymentConditionService.class).to(PaymentConditionServiceImpl.class);

    bind(AccountingReportAnalyticConfigLineService.class)
        .to(AccountingReportAnalyticConfigLineServiceImpl.class);

    bind(MoveTemplateRepository.class).to(MoveTemplateManagementRepository.class);

    bind(PfpService.class).to(PfpServiceImpl.class);

    bind(AnalyticAttrsService.class).to(AnalyticAttrsServiceImpl.class);

    bind(InvoiceTermAttrsService.class).to(InvoiceTermAttrsServiceImpl.class);

    bind(InvoiceTermGroupService.class).to(InvoiceTermGroupServiceImpl.class);

    bind(InvoiceTermRecordService.class).to(InvoiceTermRecordServiceImpl.class);

    bind(AnalyticGroupService.class).to(AnalyticGroupServiceImpl.class);

    bind(TradingNameAnalyticService.class).to(TradingNameAnalyticServiceImpl.class);

    bind(YearAccountService.class).to(YearAccountServiceImpl.class);

    bind(MoveTemplateService.class).to(MoveTemplateServiceImpl.class);

    bind(MoveTemplateCheckService.class).to(MoveTemplateCheckServiceImpl.class);

    bind(MoveTemplateTaxService.class).to(MoveTemplateTaxServiceImpl.class);

    bind(MoveTemplateGroupService.class).to(MoveTemplateGroupServiceImpl.class);

    bind(InvoiceTermReplaceService.class).to(InvoiceTermReplaceServiceImpl.class);

    bind(InvoiceLineRepository.class).to(InvoiceLineManagementRepository.class);

    bind(InvoiceLineAttrsService.class).to(InvoiceLineAttrsServiceImpl.class);

    bind(InvoiceLineTaxAttrsService.class).to(InvoiceLineTaxAttrsServiceImpl.class);

    bind(InvoiceLineGroupService.class).to(InvoiceLineGroupServiceImpl.class);

    bind(InvoiceLineTaxGroupService.class).to(InvoiceLineTaxGroupServiceImpl.class);

    bind(InvoiceLineTaxRecordService.class).to(InvoiceLineTaxRecordServiceImpl.class);

    bind(InvoiceTermPaymentGroupService.class).to(InvoiceTermPaymentGroupServiceImpl.class);

    bind(InvoiceTermPaymentAttrsService.class).to(InvoiceTermPaymentAttrsServiceImpl.class);

    bind(MoveLineFinancialDiscountService.class).to(MoveLineFinancialDiscountServiceImpl.class);

    bind(FinancialDiscountService.class).to(FinancialDiscountServiceImpl.class);

    bind(InvoicePaymentFinancialDiscountService.class)
        .to(InvoicePaymentFinancialDiscountServiceImpl.class);

    bind(InvoiceTermFinancialDiscountService.class)
        .to(InvoiceTermFinancialDiscountServiceImpl.class);

    bind(ImportFECTypeService.class).to(ImportFECTypeServiceImpl.class);

    bind(ImportMoveFecService.class).to(ImportMoveFecServiceImpl.class);

    bind(ImportMoveLineAmountService.class).to(ImportMoveLineAmountServiceImpl.class);

    bind(FixedAssetLineRepository.class).to(FixedAssetLineManagementRepository.class);

    bind(FixedAssetGroupService.class).to(FixedAssetGroupServiceImpl.class);

    bind(FixedAssetAttrsService.class).to(FixedAssetAttrsServiceImpl.class);

    bind(FixedAssetRecordService.class).to(FixedAssetRecordServiceImpl.class);

    bind(FixedAssetDerogatoryLineRepository.class)
        .to(FixedAssetDerogatoryLineManagementRepository.class);

    bind(FindFixedAssetService.class).to(FindFixedAssetServiceImpl.class);

    bind(ImportAnalyticInMoveService.class).to(ImportAnalyticInMoveServiceImpl.class);

    bind(PeriodCheckService.class).to(PeriodCheckServiceImpl.class);

    bind(InvoicePaymentComputeService.class).to(InvoicePaymentComputeServiceImpl.class);

    bind(InvoiceTermToolService.class).to(InvoiceTermToolServiceImpl.class);

    bind(InvoiceTermFilterService.class).to(InvoiceTermFilterServiceImpl.class);

    bind(AdvancePaymentRefundService.class).to(AdvancePaymentRefundServiceImpl.class);

    bind(ReconcileToolService.class).to(ReconcileToolServiceImpl.class);

    bind(UnreconcileGroupService.class).to(UnreconcileGroupServiceImpl.class);

    bind(UnreconcileService.class).to(UnreconcileServiceImpl.class);

    bind(ReconcileCheckService.class).to(ReconcileCheckServiceImpl.class);

    bind(ReconcileInvoiceTermComputationService.class)
        .to(ReconcileInvoiceTermComputationServiceImpl.class);

    bind(ReconcileGroupToolService.class).to(ReconcileGroupToolServiceImpl.class);

    bind(InvoicePaymentMoveCreateService.class).to(InvoicePaymentMoveCreateServiceImpl.class);

    bind(InvoicingPaymentSituationService.class).to(InvoicingPaymentSituationServiceImpl.class);
    bind(UmrService.class).to(UmrServiceImpl.class);

    bind(InvoiceJournalService.class).to(InvoiceJournalServiceImpl.class);

    bind(FiscalPositionRepository.class).to(FiscalPositionManagementRepository.class);

    bind(InvoiceLineTaxToolService.class).to(InvoiceLineTaxToolServiceImpl.class);

    bind(AdvancePaymentMoveLineCreateService.class)
        .to(AdvancePaymentMoveLineCreateServiceImpl.class);

    bind(InvoiceTermPfpUpdateService.class).to(InvoiceTermPfpUpdateServiceImpl.class);

    bind(InvoiceTermPfpToolService.class).to(InvoiceTermPfpToolServiceImpl.class);

    bind(InvoiceTermPfpValidateService.class).to(InvoiceTermPfpValidateServiceImpl.class);

    bind(MovePfpValidateService.class).to(MovePfpValidateServiceImpl.class);

    bind(InvoicePfpValidateService.class).to(InvoicePfpValidateServiceImpl.class);
    bind(InvoiceTermPaymentToolService.class).to(InvoiceTermPaymentToolServiceImpl.class);

    bind(ForeignExchangeGapService.class).to(ForeignExchangeGapServiceImpl.class);

    bind(ForeignExchangeGapToolService.class).to(ForeignExchangeGapToolServiceImpl.class);

    bind(LatePaymentInterestInvoiceService.class).to(LatePaymentInterestInvoiceServiceImpl.class);
    bind(PaymentModeInterestRateService.class).to(PaymentModeInterestRateServiceImpl.class);

    bind(InvoiceTaxComputeService.class).to(InvoiceTaxComputeServiceImpl.class);
    bind(AccountingBatchViewService.class).to(AccountingBatchViewServiceImpl.class);

    bind(InvoiceTermDateComputeService.class).to(InvoiceTermDateComputeServiceImpl.class);
    bind(InvoiceLineCheckService.class).to(InvoiceLineCheckServiceImpl.class);
    bind(InvoiceGlobalDiscountService.class).to(InvoiceGlobalDiscountServiceImpl.class);
    bind(MassEntryCheckService.class).to(MassEntryCheckServiceImpl.class);
    bind(MassEntryMoveUpdateService.class).to(MassEntryMoveUpdateServiceImpl.class);
    bind(MassEntryMoveValidateService.class).to(MassEntryMoveValidateServiceImpl.class);
    bind(MoveGroupOnChangeService.class).to(MoveGroupOnChangeServiceImpl.class);
    bind(BankDetailsDomainServiceAccount.class).to(BankDetailsDomainServiceAccountImpl.class);
    bind(BankDetailsServiceAccount.class).to(BankDetailsServiceAccountImpl.class);

    bind(AnalyticMoveLineParentService.class).to(AnalyticMoveLineParentServiceImpl.class);
    bind(PaymentScheduleRepository.class).to(PaymentScheduleAccountRepository.class);
    bind(InvoicePaymentAlertService.class).to(InvoicePaymentAlertServiceImpl.class);

    bind(MovePfpToolService.class).to(MovePfpToolServiceImpl.class);

    bind(InvoiceNoteService.class).to(InvoiceNoteServiceImpl.class);
    bind(InvoiceCategoryService.class).to(InvoiceCategoryServiceImpl.class);
    bind(AnalyticLineComputeService.class).to(AnalyticLineComputeServiceImpl.class);
    bind(MoveTemplateLineAnalyticService.class).to(MoveTemplateLineAnalyticServiceImpl.class);
    bind(MoveTemplateLineComputeAnalyticService.class)
        .to(MoveTemplateLineComputeAnalyticServiceImpl.class);
  }
}
