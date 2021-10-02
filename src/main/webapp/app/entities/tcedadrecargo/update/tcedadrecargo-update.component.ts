import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCEDADRECARGO, TCEDADRECARGO } from '../tcedadrecargo.model';
import { TCEDADRECARGOService } from '../service/tcedadrecargo.service';

@Component({
  selector: 'jhi-tcedadrecargo-update',
  templateUrl: './tcedadrecargo-update.component.html',
})
export class TCEDADRECARGOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idEdadRecargo: [],
    edadMin: [null, [Validators.required]],
    edadMax: [null, [Validators.required]],
    recargoAnterior: [null, [Validators.required]],
    recargoActual: [null, [Validators.required]],
  });

  constructor(protected tCEDADRECARGOService: TCEDADRECARGOService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCEDADRECARGO }) => {
      this.updateForm(tCEDADRECARGO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCEDADRECARGO = this.createFromForm();
    if (tCEDADRECARGO.idEdadRecargo !== undefined) {
      this.subscribeToSaveResponse(this.tCEDADRECARGOService.update(tCEDADRECARGO));
    } else {
      this.subscribeToSaveResponse(this.tCEDADRECARGOService.create(tCEDADRECARGO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCEDADRECARGO>>): void {
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

  protected updateForm(tCEDADRECARGO: ITCEDADRECARGO): void {
    this.editForm.patchValue({
      idEdadRecargo: tCEDADRECARGO.idEdadRecargo,
      edadMin: tCEDADRECARGO.edadMin,
      edadMax: tCEDADRECARGO.edadMax,
      recargoAnterior: tCEDADRECARGO.recargoAnterior,
      recargoActual: tCEDADRECARGO.recargoActual,
    });
  }

  protected createFromForm(): ITCEDADRECARGO {
    return {
      ...new TCEDADRECARGO(),
      idEdadRecargo: this.editForm.get(['idEdadRecargo'])!.value,
      edadMin: this.editForm.get(['edadMin'])!.value,
      edadMax: this.editForm.get(['edadMax'])!.value,
      recargoAnterior: this.editForm.get(['recargoAnterior'])!.value,
      recargoActual: this.editForm.get(['recargoActual'])!.value,
    };
  }
}
