import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCOVID, TCCOVID } from '../tccovid.model';
import { TCCOVIDService } from '../service/tccovid.service';

@Component({
  selector: 'jhi-tccovid-update',
  templateUrl: './tccovid-update.component.html',
})
export class TCCOVIDUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCovid: [],
    edad: [null, [Validators.required]],
    basica: [null, [Validators.required]],
    recargoEdad: [null, [Validators.required]],
    recargoGiro: [null, [Validators.required]],
    recargoTotal: [null, [Validators.required]],
    basicaRecargada: [null, [Validators.required]],
  });

  constructor(protected tCCOVIDService: TCCOVIDService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCOVID }) => {
      this.updateForm(tCCOVID);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCOVID = this.createFromForm();
    if (tCCOVID.idCovid !== undefined) {
      this.subscribeToSaveResponse(this.tCCOVIDService.update(tCCOVID));
    } else {
      this.subscribeToSaveResponse(this.tCCOVIDService.create(tCCOVID));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCOVID>>): void {
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

  protected updateForm(tCCOVID: ITCCOVID): void {
    this.editForm.patchValue({
      idCovid: tCCOVID.idCovid,
      edad: tCCOVID.edad,
      basica: tCCOVID.basica,
      recargoEdad: tCCOVID.recargoEdad,
      recargoGiro: tCCOVID.recargoGiro,
      recargoTotal: tCCOVID.recargoTotal,
      basicaRecargada: tCCOVID.basicaRecargada,
    });
  }

  protected createFromForm(): ITCCOVID {
    return {
      ...new TCCOVID(),
      idCovid: this.editForm.get(['idCovid'])!.value,
      edad: this.editForm.get(['edad'])!.value,
      basica: this.editForm.get(['basica'])!.value,
      recargoEdad: this.editForm.get(['recargoEdad'])!.value,
      recargoGiro: this.editForm.get(['recargoGiro'])!.value,
      recargoTotal: this.editForm.get(['recargoTotal'])!.value,
      basicaRecargada: this.editForm.get(['basicaRecargada'])!.value,
    };
  }
}
