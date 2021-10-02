import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCUOTATARIFASDESC, TCCUOTATARIFASDESC } from '../tccuotatarifasdesc.model';
import { TCCUOTATARIFASDESCService } from '../service/tccuotatarifasdesc.service';

@Component({
  selector: 'jhi-tccuotatarifasdesc-update',
  templateUrl: './tccuotatarifasdesc-update.component.html',
})
export class TCCUOTATARIFASDESCUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCuotaTarifaSdesc: [],
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

  constructor(
    protected tCCUOTATARIFASDESCService: TCCUOTATARIFASDESCService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCUOTATARIFASDESC }) => {
      this.updateForm(tCCUOTATARIFASDESC);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCUOTATARIFASDESC = this.createFromForm();
    if (tCCUOTATARIFASDESC.idCuotaTarifaSdesc !== undefined) {
      this.subscribeToSaveResponse(this.tCCUOTATARIFASDESCService.update(tCCUOTATARIFASDESC));
    } else {
      this.subscribeToSaveResponse(this.tCCUOTATARIFASDESCService.create(tCCUOTATARIFASDESC));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCUOTATARIFASDESC>>): void {
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

  protected updateForm(tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC): void {
    this.editForm.patchValue({
      idCuotaTarifaSdesc: tCCUOTATARIFASDESC.idCuotaTarifaSdesc,
      edad: tCCUOTATARIFASDESC.edad,
      valorBa: tCCUOTATARIFASDESC.valorBa,
      valorBaCovid: tCCUOTATARIFASDESC.valorBaCovid,
      valorBipTres: tCCUOTATARIFASDESC.valorBipTres,
      valorBipSeis: tCCUOTATARIFASDESC.valorBipSeis,
      valorBit: tCCUOTATARIFASDESC.valorBit,
      valorMa: tCCUOTATARIFASDESC.valorMa,
      valorDi: tCCUOTATARIFASDESC.valorDi,
      valorTi: tCCUOTATARIFASDESC.valorTi,
      valorGff: tCCUOTATARIFASDESC.valorGff,
      valorGffCovid: tCCUOTATARIFASDESC.valorGffCovid,
      valorCa: tCCUOTATARIFASDESC.valorCa,
      valorGe: tCCUOTATARIFASDESC.valorGe,
      valorIqUno: tCCUOTATARIFASDESC.valorIqUno,
      valorIqDos: tCCUOTATARIFASDESC.valorIqDos,
    });
  }

  protected createFromForm(): ITCCUOTATARIFASDESC {
    return {
      ...new TCCUOTATARIFASDESC(),
      idCuotaTarifaSdesc: this.editForm.get(['idCuotaTarifaSdesc'])!.value,
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
