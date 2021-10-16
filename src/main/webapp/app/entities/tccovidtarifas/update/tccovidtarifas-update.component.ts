import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCOVIDTARIFAS, TCCOVIDTARIFAS } from '../tccovidtarifas.model';
import { TCCOVIDTARIFASService } from '../service/tccovidtarifas.service';

@Component({
  selector: 'jhi-tccovidtarifas-update',
  templateUrl: './tccovidtarifas-update.component.html',
})
export class TCCOVIDTARIFASUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCovidTarifas: [],
    edad: [null, [Validators.required]],
    qxcnsfg: [null, [Validators.required]],
    titular: [null, [Validators.required]],
    conyuge: [null, [Validators.required]],
    hijoMayor: [null, [Validators.required]],
    hijoMenor: [null, [Validators.required]],
    qxTitular: [null, [Validators.required]],
    qxConyuge: [null, [Validators.required]],
    qxHijoMayor: [null, [Validators.required]],
    qxHijoMenor: [null, [Validators.required]],
    qxTitularRecargada: [null, [Validators.required]],
    qxConyugeRecargada: [null, [Validators.required]],
    qxHijoMayorRecargada: [null, [Validators.required]],
    qxHijoMenorRecargada: [null, [Validators.required]],
    valorGff: [null, [Validators.required]],
    valorGffCovid: [null, [Validators.required]],
  });

  constructor(
    protected tCCOVIDTARIFASService: TCCOVIDTARIFASService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCOVIDTARIFAS }) => {
      this.updateForm(tCCOVIDTARIFAS);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCOVIDTARIFAS = this.createFromForm();
    if (tCCOVIDTARIFAS.idCovidTarifas !== undefined) {
      this.subscribeToSaveResponse(this.tCCOVIDTARIFASService.update(tCCOVIDTARIFAS));
    } else {
      this.subscribeToSaveResponse(this.tCCOVIDTARIFASService.create(tCCOVIDTARIFAS));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCOVIDTARIFAS>>): void {
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

  protected updateForm(tCCOVIDTARIFAS: ITCCOVIDTARIFAS): void {
    this.editForm.patchValue({
      idCovidTarifas: tCCOVIDTARIFAS.idCovidTarifas,
      edad: tCCOVIDTARIFAS.edad,
      qxcnsfg: tCCOVIDTARIFAS.qxcnsfg,
      titular: tCCOVIDTARIFAS.titular,
      conyuge: tCCOVIDTARIFAS.conyuge,
      hijoMayor: tCCOVIDTARIFAS.hijoMayor,
      hijoMenor: tCCOVIDTARIFAS.hijoMenor,
      qxTitular: tCCOVIDTARIFAS.qxTitular,
      qxConyuge: tCCOVIDTARIFAS.qxConyuge,
      qxHijoMayor: tCCOVIDTARIFAS.qxHijoMayor,
      qxHijoMenor: tCCOVIDTARIFAS.qxHijoMenor,
      qxTitularRecargada: tCCOVIDTARIFAS.qxTitularRecargada,
      qxConyugeRecargada: tCCOVIDTARIFAS.qxConyugeRecargada,
      qxHijoMayorRecargada: tCCOVIDTARIFAS.qxHijoMayorRecargada,
      qxHijoMenorRecargada: tCCOVIDTARIFAS.qxHijoMenorRecargada,
      valorGff: tCCOVIDTARIFAS.valorGff,
      valorGffCovid: tCCOVIDTARIFAS.valorGffCovid,
    });
  }

  protected createFromForm(): ITCCOVIDTARIFAS {
    return {
      ...new TCCOVIDTARIFAS(),
      idCovidTarifas: this.editForm.get(['idCovidTarifas'])!.value,
      edad: this.editForm.get(['edad'])!.value,
      qxcnsfg: this.editForm.get(['qxcnsfg'])!.value,
      titular: this.editForm.get(['titular'])!.value,
      conyuge: this.editForm.get(['conyuge'])!.value,
      hijoMayor: this.editForm.get(['hijoMayor'])!.value,
      hijoMenor: this.editForm.get(['hijoMenor'])!.value,
      qxTitular: this.editForm.get(['qxTitular'])!.value,
      qxConyuge: this.editForm.get(['qxConyuge'])!.value,
      qxHijoMayor: this.editForm.get(['qxHijoMayor'])!.value,
      qxHijoMenor: this.editForm.get(['qxHijoMenor'])!.value,
      qxTitularRecargada: this.editForm.get(['qxTitularRecargada'])!.value,
      qxConyugeRecargada: this.editForm.get(['qxConyugeRecargada'])!.value,
      qxHijoMayorRecargada: this.editForm.get(['qxHijoMayorRecargada'])!.value,
      qxHijoMenorRecargada: this.editForm.get(['qxHijoMenorRecargada'])!.value,
      valorGff: this.editForm.get(['valorGff'])!.value,
      valorGffCovid: this.editForm.get(['valorGffCovid'])!.value,
    };
  }
}
