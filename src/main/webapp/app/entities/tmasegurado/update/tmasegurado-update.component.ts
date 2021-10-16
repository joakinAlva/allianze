import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITMASEGURADO, TMASEGURADO } from '../tmasegurado.model';
import { TMASEGURADOService } from '../service/tmasegurado.service';

@Component({
  selector: 'jhi-tmasegurado-update',
  templateUrl: './tmasegurado-update.component.html',
})
export class TMASEGURADOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idAsegurados: [],
    numero: [null, [Validators.required]],
    excedente: [null, [Validators.required]],
    subgrupo: [null, [Validators.required]],
    nombre: [null, [Validators.required]],
    fNacimiento: [null, [Validators.required]],
    sueldo: [null, [Validators.required]],
    reglaMonto: [null, [Validators.required]],
    edad: [null, [Validators.required]],
    saTotal: [null, [Validators.required]],
    saTopado: [null, [Validators.required]],
    basica: [null, [Validators.required]],
    basicaCovid: [null, [Validators.required]],
    extrabas: [null, [Validators.required]],
    primaBasica: [null, [Validators.required]],
    invalidez: [null, [Validators.required]],
    extraInv: [null, [Validators.required]],
    exencion: [null, [Validators.required]],
    extraExe: [null, [Validators.required]],
    muerteAcc: [null, [Validators.required]],
    extraAcc: [null, [Validators.required]],
    valorGff: [null, [Validators.required]],
    valorGffCovid: [null, [Validators.required]],
    extraGff: [null, [Validators.required]],
    primaGff: [null, [Validators.required]],
    primaCa: [null, [Validators.required]],
    extraCa: [null, [Validators.required]],
    primaGe: [null, [Validators.required]],
    extraGe: [null, [Validators.required]],
    primaIqs: [null, [Validators.required]],
    extraIqa: [null, [Validators.required]],
    primaIqv: [null, [Validators.required]],
    extraIqv: [null, [Validators.required]],
  });

  constructor(protected tMASEGURADOService: TMASEGURADOService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMASEGURADO }) => {
      this.updateForm(tMASEGURADO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tMASEGURADO = this.createFromForm();
    if (tMASEGURADO.idAsegurados !== undefined) {
      this.subscribeToSaveResponse(this.tMASEGURADOService.update(tMASEGURADO));
    } else {
      this.subscribeToSaveResponse(this.tMASEGURADOService.create(tMASEGURADO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITMASEGURADO>>): void {
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

  protected updateForm(tMASEGURADO: ITMASEGURADO): void {
    this.editForm.patchValue({
      idAsegurados: tMASEGURADO.idAsegurados,
      numero: tMASEGURADO.numero,
      excedente: tMASEGURADO.excedente,
      subgrupo: tMASEGURADO.subgrupo,
      nombre: tMASEGURADO.nombre,
      fNacimiento: tMASEGURADO.fNacimiento,
      sueldo: tMASEGURADO.sueldo,
      reglaMonto: tMASEGURADO.reglaMonto,
      edad: tMASEGURADO.edad,
      saTotal: tMASEGURADO.saTotal,
      saTopado: tMASEGURADO.saTopado,
      basica: tMASEGURADO.basica,
      basicaCovid: tMASEGURADO.basicaCovid,
      extrabas: tMASEGURADO.extrabas,
      primaBasica: tMASEGURADO.primaBasica,
      invalidez: tMASEGURADO.invalidez,
      extraInv: tMASEGURADO.extraInv,
      exencion: tMASEGURADO.exencion,
      extraExe: tMASEGURADO.extraExe,
      muerteAcc: tMASEGURADO.muerteAcc,
      extraAcc: tMASEGURADO.extraAcc,
      valorGff: tMASEGURADO.valorGff,
      valorGffCovid: tMASEGURADO.valorGffCovid,
      extraGff: tMASEGURADO.extraGff,
      primaGff: tMASEGURADO.primaGff,
      primaCa: tMASEGURADO.primaCa,
      extraCa: tMASEGURADO.extraCa,
      primaGe: tMASEGURADO.primaGe,
      extraGe: tMASEGURADO.extraGe,
      primaIqs: tMASEGURADO.primaIqs,
      extraIqa: tMASEGURADO.extraIqa,
      primaIqv: tMASEGURADO.primaIqv,
      extraIqv: tMASEGURADO.extraIqv,
    });
  }

  protected createFromForm(): ITMASEGURADO {
    return {
      ...new TMASEGURADO(),
      idAsegurados: this.editForm.get(['idAsegurados'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      excedente: this.editForm.get(['excedente'])!.value,
      subgrupo: this.editForm.get(['subgrupo'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      fNacimiento: this.editForm.get(['fNacimiento'])!.value,
      sueldo: this.editForm.get(['sueldo'])!.value,
      reglaMonto: this.editForm.get(['reglaMonto'])!.value,
      edad: this.editForm.get(['edad'])!.value,
      saTotal: this.editForm.get(['saTotal'])!.value,
      saTopado: this.editForm.get(['saTopado'])!.value,
      basica: this.editForm.get(['basica'])!.value,
      basicaCovid: this.editForm.get(['basicaCovid'])!.value,
      extrabas: this.editForm.get(['extrabas'])!.value,
      primaBasica: this.editForm.get(['primaBasica'])!.value,
      invalidez: this.editForm.get(['invalidez'])!.value,
      extraInv: this.editForm.get(['extraInv'])!.value,
      exencion: this.editForm.get(['exencion'])!.value,
      extraExe: this.editForm.get(['extraExe'])!.value,
      muerteAcc: this.editForm.get(['muerteAcc'])!.value,
      extraAcc: this.editForm.get(['extraAcc'])!.value,
      valorGff: this.editForm.get(['valorGff'])!.value,
      valorGffCovid: this.editForm.get(['valorGffCovid'])!.value,
      extraGff: this.editForm.get(['extraGff'])!.value,
      primaGff: this.editForm.get(['primaGff'])!.value,
      primaCa: this.editForm.get(['primaCa'])!.value,
      extraCa: this.editForm.get(['extraCa'])!.value,
      primaGe: this.editForm.get(['primaGe'])!.value,
      extraGe: this.editForm.get(['extraGe'])!.value,
      primaIqs: this.editForm.get(['primaIqs'])!.value,
      extraIqa: this.editForm.get(['extraIqa'])!.value,
      primaIqv: this.editForm.get(['primaIqv'])!.value,
      extraIqv: this.editForm.get(['extraIqv'])!.value,
    };
  }
}
