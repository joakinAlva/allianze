import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITMCOTIZACIONINFO, TMCOTIZACIONINFO } from '../tmcotizacioninfo.model';
import { TMCOTIZACIONINFOService } from '../service/tmcotizacioninfo.service';

@Component({
  selector: 'jhi-tmcotizacioninfo-update',
  templateUrl: './tmcotizacioninfo-update.component.html',
})
export class TMCOTIZACIONINFOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCotizacionInfo: [],
    numero: [null, [Validators.required]],
    cotizacion: [null, [Validators.required]],
    region: [null, [Validators.required]],
    fechaSolicitud: [null, [Validators.required]],
    responsable: [null, [Validators.required]],
    fechaEntrega: [null, [Validators.required]],
    contratante: [null, [Validators.required]],
    tipoUno: [null, [Validators.required]],
    tipoDos: [null, [Validators.required]],
    poliza: [null, [Validators.required]],
    inicioVigencia: [null, [Validators.required]],
    finVigencia: [null, [Validators.required]],
    intermediario: [null, [Validators.required]],
    rfc: [null, [Validators.required]],
    ejecutivo: [null, [Validators.required]],
    suscriptor: [null, [Validators.required]],
    plan: [null, [Validators.required]],
    ocupacion: [null, [Validators.required]],
    primaCovidVida: [null, [Validators.required]],
    primaCovidGff: [null, [Validators.required]],
    descuentoPrimaCovid: [null, [Validators.required]],
    extraPrimaVida: [null, [Validators.required]],
    extraPrimaGff: [null, [Validators.required]],
    porcentajeExtraPrimaVida: [null, [Validators.required]],
    porcentajeExtraPrimaGff: [null, [Validators.required]],
    valorFtr: [null, [Validators.required]],
    sami: [null, [Validators.required]],
    samiMin: [null, [Validators.required]],
    samiMax: [null, [Validators.required]],
    margen: [null, [Validators.required]],
    margenMin: [null, [Validators.required]],
    margenMax: [null, [Validators.required]],
    comision: [null, [Validators.required]],
    comisionMin: [null, [Validators.required]],
    comisionMax: [null, [Validators.required]],
    descuento: [null, [Validators.required]],
    descuentoMin: [null, [Validators.required]],
    descuentoMax: [null, [Validators.required]],
    primaneta: [null, [Validators.required]],
    primaNetaMin: [null, [Validators.required]],
    primaNetaMax: [null, [Validators.required]],
    derechoPoliza: [null, [Validators.required]],
    derechoPolizaMin: [null, [Validators.required]],
    derechoPolizaMax: [null, [Validators.required]],
    mayores: [null, [Validators.required]],
    mayoresMin: [null, [Validators.required]],
    mayoresMax: [null, [Validators.required]],
    asegurados: [null, [Validators.required]],
    aseguradosMin: [null, [Validators.required]],
    aseguradosMax: [null, [Validators.required]],
    saPromedio: [null, [Validators.required]],
    saPromedioMin: [null, [Validators.required]],
    saPromedioMax: [null, [Validators.required]],
    varSa: [null, [Validators.required]],
    varSaMin: [null, [Validators.required]],
    varSaMax: [null, [Validators.required]],
    saTotal: [null, [Validators.required]],
    saMaxima: [null, [Validators.required]],
    saMaximaMin: [null, [Validators.required]],
    saMaximaMax: [null, [Validators.required]],
    subgrupos: [null, [Validators.required]],
    subgruposMin: [null, [Validators.required]],
    edadMediaMin: [null, [Validators.required]],
    edadActuarial: [null, [Validators.required]],
    edadActuarialMin: [null, [Validators.required]],
    edadActPond: [null, [Validators.required]],
    edadActPondMin: [null, [Validators.required]],
    edadMin: [null, [Validators.required]],
    edadMinDos: [null, [Validators.required]],
    edadMaxDos: [null, [Validators.required]],
    edadMaxTres: [null, [Validators.required]],
    coeficienteVariacion: [null, [Validators.required]],
    factorTrUnoGiro: [null, [Validators.required]],
    factorSaProm: [null, [Validators.required]],
    pNetaGlobalSdmc: [null, [Validators.required]],
    pNetaGlobalCdmcCuota: [null, [Validators.required]],
    pNetaGlobalSmccdesc: [null, [Validators.required]],
    pNetaGlobalSmccdescCuota: [null, [Validators.required]],
    pNetaBasicaSdmc: [null, [Validators.required]],
    pNetaBasicaSdmcCuota: [null, [Validators.required]],
    pNetaBasicaCdmc: [null, [Validators.required]],
    pNetaBasicaCdmcCuota: [null, [Validators.required]],
  });

  constructor(
    protected tMCOTIZACIONINFOService: TMCOTIZACIONINFOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMCOTIZACIONINFO }) => {
      this.updateForm(tMCOTIZACIONINFO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tMCOTIZACIONINFO = this.createFromForm();
    if (tMCOTIZACIONINFO.idCotizacionInfo !== undefined) {
      this.subscribeToSaveResponse(this.tMCOTIZACIONINFOService.update(tMCOTIZACIONINFO));
    } else {
      this.subscribeToSaveResponse(this.tMCOTIZACIONINFOService.create(tMCOTIZACIONINFO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITMCOTIZACIONINFO>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(tMCOTIZACIONINFO: ITMCOTIZACIONINFO): void {
    this.editForm.patchValue({
      idCotizacionInfo: tMCOTIZACIONINFO.idCotizacionInfo,
      numero: tMCOTIZACIONINFO.numero,
      cotizacion: tMCOTIZACIONINFO.cotizacion,
      region: tMCOTIZACIONINFO.region,
      fechaSolicitud: tMCOTIZACIONINFO.fechaSolicitud,
      responsable: tMCOTIZACIONINFO.responsable,
      fechaEntrega: tMCOTIZACIONINFO.fechaEntrega,
      contratante: tMCOTIZACIONINFO.contratante,
      tipoUno: tMCOTIZACIONINFO.tipoUno,
      tipoDos: tMCOTIZACIONINFO.tipoDos,
      poliza: tMCOTIZACIONINFO.poliza,
      inicioVigencia: tMCOTIZACIONINFO.inicioVigencia,
      finVigencia: tMCOTIZACIONINFO.finVigencia,
      intermediario: tMCOTIZACIONINFO.intermediario,
      rfc: tMCOTIZACIONINFO.rfc,
      ejecutivo: tMCOTIZACIONINFO.ejecutivo,
      suscriptor: tMCOTIZACIONINFO.suscriptor,
      plan: tMCOTIZACIONINFO.plan,
      ocupacion: tMCOTIZACIONINFO.ocupacion,
      primaCovidVida: tMCOTIZACIONINFO.primaCovidVida,
      primaCovidGff: tMCOTIZACIONINFO.primaCovidGff,
      descuentoPrimaCovid: tMCOTIZACIONINFO.descuentoPrimaCovid,
      extraPrimaVida: tMCOTIZACIONINFO.extraPrimaVida,
      extraPrimaGff: tMCOTIZACIONINFO.extraPrimaGff,
      porcentajeExtraPrimaVida: tMCOTIZACIONINFO.porcentajeExtraPrimaVida,
      porcentajeExtraPrimaGff: tMCOTIZACIONINFO.porcentajeExtraPrimaGff,
      valorFtr: tMCOTIZACIONINFO.valorFtr,
      sami: tMCOTIZACIONINFO.sami,
      samiMin: tMCOTIZACIONINFO.samiMin,
      samiMax: tMCOTIZACIONINFO.samiMax,
      margen: tMCOTIZACIONINFO.margen,
      margenMin: tMCOTIZACIONINFO.margenMin,
      margenMax: tMCOTIZACIONINFO.margenMax,
      comision: tMCOTIZACIONINFO.comision,
      comisionMin: tMCOTIZACIONINFO.comisionMin,
      comisionMax: tMCOTIZACIONINFO.comisionMax,
      descuento: tMCOTIZACIONINFO.descuento,
      descuentoMin: tMCOTIZACIONINFO.descuentoMin,
      descuentoMax: tMCOTIZACIONINFO.descuentoMax,
      primaneta: tMCOTIZACIONINFO.primaneta,
      primaNetaMin: tMCOTIZACIONINFO.primaNetaMin,
      primaNetaMax: tMCOTIZACIONINFO.primaNetaMax,
      derechoPoliza: tMCOTIZACIONINFO.derechoPoliza,
      derechoPolizaMin: tMCOTIZACIONINFO.derechoPolizaMin,
      derechoPolizaMax: tMCOTIZACIONINFO.derechoPolizaMax,
      mayores: tMCOTIZACIONINFO.mayores,
      mayoresMin: tMCOTIZACIONINFO.mayoresMin,
      mayoresMax: tMCOTIZACIONINFO.mayoresMax,
      asegurados: tMCOTIZACIONINFO.asegurados,
      aseguradosMin: tMCOTIZACIONINFO.aseguradosMin,
      aseguradosMax: tMCOTIZACIONINFO.aseguradosMax,
      saPromedio: tMCOTIZACIONINFO.saPromedio,
      saPromedioMin: tMCOTIZACIONINFO.saPromedioMin,
      saPromedioMax: tMCOTIZACIONINFO.saPromedioMax,
      varSa: tMCOTIZACIONINFO.varSa,
      varSaMin: tMCOTIZACIONINFO.varSaMin,
      varSaMax: tMCOTIZACIONINFO.varSaMax,
      saTotal: tMCOTIZACIONINFO.saTotal,
      saMaxima: tMCOTIZACIONINFO.saMaxima,
      saMaximaMin: tMCOTIZACIONINFO.saMaximaMin,
      saMaximaMax: tMCOTIZACIONINFO.saMaximaMax,
      subgrupos: tMCOTIZACIONINFO.subgrupos,
      subgruposMin: tMCOTIZACIONINFO.subgruposMin,
      edadMediaMin: tMCOTIZACIONINFO.edadMediaMin,
      edadActuarial: tMCOTIZACIONINFO.edadActuarial,
      edadActuarialMin: tMCOTIZACIONINFO.edadActuarialMin,
      edadActPond: tMCOTIZACIONINFO.edadActPond,
      edadActPondMin: tMCOTIZACIONINFO.edadActPondMin,
      edadMin: tMCOTIZACIONINFO.edadMin,
      edadMinDos: tMCOTIZACIONINFO.edadMinDos,
      edadMaxDos: tMCOTIZACIONINFO.edadMaxDos,
      edadMaxTres: tMCOTIZACIONINFO.edadMaxTres,
      coeficienteVariacion: tMCOTIZACIONINFO.coeficienteVariacion,
      factorTrUnoGiro: tMCOTIZACIONINFO.factorTrUnoGiro,
      factorSaProm: tMCOTIZACIONINFO.factorSaProm,
      pNetaGlobalSdmc: tMCOTIZACIONINFO.pNetaGlobalSdmc,
      pNetaGlobalCdmcCuota: tMCOTIZACIONINFO.pNetaGlobalCdmcCuota,
      pNetaGlobalSmccdesc: tMCOTIZACIONINFO.pNetaGlobalSmccdesc,
      pNetaGlobalSmccdescCuota: tMCOTIZACIONINFO.pNetaGlobalSmccdescCuota,
      pNetaBasicaSdmc: tMCOTIZACIONINFO.pNetaBasicaSdmc,
      pNetaBasicaSdmcCuota: tMCOTIZACIONINFO.pNetaBasicaSdmcCuota,
      pNetaBasicaCdmc: tMCOTIZACIONINFO.pNetaBasicaCdmc,
      pNetaBasicaCdmcCuota: tMCOTIZACIONINFO.pNetaBasicaCdmcCuota,
    });
  }

  protected createFromForm(): ITMCOTIZACIONINFO {
    return {
      ...new TMCOTIZACIONINFO(),
      idCotizacionInfo: this.editForm.get(['idCotizacionInfo'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      cotizacion: this.editForm.get(['cotizacion'])!.value,
      region: this.editForm.get(['region'])!.value,
      fechaSolicitud: this.editForm.get(['fechaSolicitud'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
      fechaEntrega: this.editForm.get(['fechaEntrega'])!.value,
      contratante: this.editForm.get(['contratante'])!.value,
      tipoUno: this.editForm.get(['tipoUno'])!.value,
      tipoDos: this.editForm.get(['tipoDos'])!.value,
      poliza: this.editForm.get(['poliza'])!.value,
      inicioVigencia: this.editForm.get(['inicioVigencia'])!.value,
      finVigencia: this.editForm.get(['finVigencia'])!.value,
      intermediario: this.editForm.get(['intermediario'])!.value,
      rfc: this.editForm.get(['rfc'])!.value,
      ejecutivo: this.editForm.get(['ejecutivo'])!.value,
      suscriptor: this.editForm.get(['suscriptor'])!.value,
      plan: this.editForm.get(['plan'])!.value,
      ocupacion: this.editForm.get(['ocupacion'])!.value,
      primaCovidVida: this.editForm.get(['primaCovidVida'])!.value,
      primaCovidGff: this.editForm.get(['primaCovidGff'])!.value,
      descuentoPrimaCovid: this.editForm.get(['descuentoPrimaCovid'])!.value,
      extraPrimaVida: this.editForm.get(['extraPrimaVida'])!.value,
      extraPrimaGff: this.editForm.get(['extraPrimaGff'])!.value,
      porcentajeExtraPrimaVida: this.editForm.get(['porcentajeExtraPrimaVida'])!.value,
      porcentajeExtraPrimaGff: this.editForm.get(['porcentajeExtraPrimaGff'])!.value,
      valorFtr: this.editForm.get(['valorFtr'])!.value,
      sami: this.editForm.get(['sami'])!.value,
      samiMin: this.editForm.get(['samiMin'])!.value,
      samiMax: this.editForm.get(['samiMax'])!.value,
      margen: this.editForm.get(['margen'])!.value,
      margenMin: this.editForm.get(['margenMin'])!.value,
      margenMax: this.editForm.get(['margenMax'])!.value,
      comision: this.editForm.get(['comision'])!.value,
      comisionMin: this.editForm.get(['comisionMin'])!.value,
      comisionMax: this.editForm.get(['comisionMax'])!.value,
      descuento: this.editForm.get(['descuento'])!.value,
      descuentoMin: this.editForm.get(['descuentoMin'])!.value,
      descuentoMax: this.editForm.get(['descuentoMax'])!.value,
      primaneta: this.editForm.get(['primaneta'])!.value,
      primaNetaMin: this.editForm.get(['primaNetaMin'])!.value,
      primaNetaMax: this.editForm.get(['primaNetaMax'])!.value,
      derechoPoliza: this.editForm.get(['derechoPoliza'])!.value,
      derechoPolizaMin: this.editForm.get(['derechoPolizaMin'])!.value,
      derechoPolizaMax: this.editForm.get(['derechoPolizaMax'])!.value,
      mayores: this.editForm.get(['mayores'])!.value,
      mayoresMin: this.editForm.get(['mayoresMin'])!.value,
      mayoresMax: this.editForm.get(['mayoresMax'])!.value,
      asegurados: this.editForm.get(['asegurados'])!.value,
      aseguradosMin: this.editForm.get(['aseguradosMin'])!.value,
      aseguradosMax: this.editForm.get(['aseguradosMax'])!.value,
      saPromedio: this.editForm.get(['saPromedio'])!.value,
      saPromedioMin: this.editForm.get(['saPromedioMin'])!.value,
      saPromedioMax: this.editForm.get(['saPromedioMax'])!.value,
      varSa: this.editForm.get(['varSa'])!.value,
      varSaMin: this.editForm.get(['varSaMin'])!.value,
      varSaMax: this.editForm.get(['varSaMax'])!.value,
      saTotal: this.editForm.get(['saTotal'])!.value,
      saMaxima: this.editForm.get(['saMaxima'])!.value,
      saMaximaMin: this.editForm.get(['saMaximaMin'])!.value,
      saMaximaMax: this.editForm.get(['saMaximaMax'])!.value,
      subgrupos: this.editForm.get(['subgrupos'])!.value,
      subgruposMin: this.editForm.get(['subgruposMin'])!.value,
      edadMediaMin: this.editForm.get(['edadMediaMin'])!.value,
      edadActuarial: this.editForm.get(['edadActuarial'])!.value,
      edadActuarialMin: this.editForm.get(['edadActuarialMin'])!.value,
      edadActPond: this.editForm.get(['edadActPond'])!.value,
      edadActPondMin: this.editForm.get(['edadActPondMin'])!.value,
      edadMin: this.editForm.get(['edadMin'])!.value,
      edadMinDos: this.editForm.get(['edadMinDos'])!.value,
      edadMaxDos: this.editForm.get(['edadMaxDos'])!.value,
      edadMaxTres: this.editForm.get(['edadMaxTres'])!.value,
      coeficienteVariacion: this.editForm.get(['coeficienteVariacion'])!.value,
      factorTrUnoGiro: this.editForm.get(['factorTrUnoGiro'])!.value,
      factorSaProm: this.editForm.get(['factorSaProm'])!.value,
      pNetaGlobalSdmc: this.editForm.get(['pNetaGlobalSdmc'])!.value,
      pNetaGlobalCdmcCuota: this.editForm.get(['pNetaGlobalCdmcCuota'])!.value,
      pNetaGlobalSmccdesc: this.editForm.get(['pNetaGlobalSmccdesc'])!.value,
      pNetaGlobalSmccdescCuota: this.editForm.get(['pNetaGlobalSmccdescCuota'])!.value,
      pNetaBasicaSdmc: this.editForm.get(['pNetaBasicaSdmc'])!.value,
      pNetaBasicaSdmcCuota: this.editForm.get(['pNetaBasicaSdmcCuota'])!.value,
      pNetaBasicaCdmc: this.editForm.get(['pNetaBasicaCdmc'])!.value,
      pNetaBasicaCdmcCuota: this.editForm.get(['pNetaBasicaCdmcCuota'])!.value,
    };
  }
}
