import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCUOTARIESGO, TCCUOTARIESGO } from '../tccuotariesgo.model';
import { TCCUOTARIESGOService } from '../service/tccuotariesgo.service';

@Component({
  selector: 'jhi-tccuotariesgo-update',
  templateUrl: './tccuotariesgo-update.component.html',
})
export class TCCUOTARIESGOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCuotaRiesgo: [],
    edad: [null, [Validators.required]],
    valorBa: [null, [Validators.required]],
    valorBaCovid: [null, [Validators.required]],
    valorBipTres: [null, [Validators.required]],
    valorBipSeis: [null, [Validators.required]],
    valorBit: [null, [Validators.required]],
    valorMa: [null, [Validators.required]],
    valorDi: [null, [Validators.required]],
    valorTi: [null, [Validators.required]],
    valorGff: [null, [Validators.required]],
    valorGffCovid: [null, [Validators.required]],
    valorCa: [null, [Validators.required]],
    valorGe: [null, [Validators.required]],
    valorIqUno: [null, [Validators.required]],
    valorIqDos: [null, [Validators.required]],
  });

  constructor(protected tCCUOTARIESGOService: TCCUOTARIESGOService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCUOTARIESGO }) => {
      this.updateForm(tCCUOTARIESGO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCUOTARIESGO = this.createFromForm();
    if (tCCUOTARIESGO.idCuotaRiesgo !== undefined) {
      this.subscribeToSaveResponse(this.tCCUOTARIESGOService.update(tCCUOTARIESGO));
    } else {
      this.subscribeToSaveResponse(this.tCCUOTARIESGOService.create(tCCUOTARIESGO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCUOTARIESGO>>): void {
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

  protected updateForm(tCCUOTARIESGO: ITCCUOTARIESGO): void {
    this.editForm.patchValue({
      idCuotaRiesgo: tCCUOTARIESGO.idCuotaRiesgo,
      edad: tCCUOTARIESGO.edad,
      valorBa: tCCUOTARIESGO.valorBa,
      valorBaCovid: tCCUOTARIESGO.valorBaCovid,
      valorBipTres: tCCUOTARIESGO.valorBipTres,
      valorBipSeis: tCCUOTARIESGO.valorBipSeis,
      valorBit: tCCUOTARIESGO.valorBit,
      valorMa: tCCUOTARIESGO.valorMa,
      valorDi: tCCUOTARIESGO.valorDi,
      valorTi: tCCUOTARIESGO.valorTi,
      valorGff: tCCUOTARIESGO.valorGff,
      valorGffCovid: tCCUOTARIESGO.valorGffCovid,
      valorCa: tCCUOTARIESGO.valorCa,
      valorGe: tCCUOTARIESGO.valorGe,
      valorIqUno: tCCUOTARIESGO.valorIqUno,
      valorIqDos: tCCUOTARIESGO.valorIqDos,
    });
  }

  protected createFromForm(): ITCCUOTARIESGO {
    return {
      ...new TCCUOTARIESGO(),
      idCuotaRiesgo: this.editForm.get(['idCuotaRiesgo'])!.value,
      edad: this.editForm.get(['edad'])!.value,
      valorBa: this.editForm.get(['valorBa'])!.value,
      valorBaCovid: this.editForm.get(['valorBaCovid'])!.value,
      valorBipTres: this.editForm.get(['valorBipTres'])!.value,
      valorBipSeis: this.editForm.get(['valorBipSeis'])!.value,
      valorBit: this.editForm.get(['valorBit'])!.value,
      valorMa: this.editForm.get(['valorMa'])!.value,
      valorDi: this.editForm.get(['valorDi'])!.value,
      valorTi: this.editForm.get(['valorTi'])!.value,
      valorGff: this.editForm.get(['valorGff'])!.value,
      valorGffCovid: this.editForm.get(['valorGffCovid'])!.value,
      valorCa: this.editForm.get(['valorCa'])!.value,
      valorGe: this.editForm.get(['valorGe'])!.value,
      valorIqUno: this.editForm.get(['valorIqUno'])!.value,
      valorIqDos: this.editForm.get(['valorIqDos'])!.value,
    };
  }
}
