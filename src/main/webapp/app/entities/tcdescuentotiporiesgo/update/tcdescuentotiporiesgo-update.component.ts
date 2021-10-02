import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCDESCUENTOTIPORIESGO, TCDESCUENTOTIPORIESGO } from '../tcdescuentotiporiesgo.model';
import { TCDESCUENTOTIPORIESGOService } from '../service/tcdescuentotiporiesgo.service';

@Component({
  selector: 'jhi-tcdescuentotiporiesgo-update',
  templateUrl: './tcdescuentotiporiesgo-update.component.html',
})
export class TCDESCUENTOTIPORIESGOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idDescuentoTipoRiesgo: [],
    tipo: [null, [Validators.required]],
    descuento: [null, [Validators.required]],
  });

  constructor(
    protected tCDESCUENTOTIPORIESGOService: TCDESCUENTOTIPORIESGOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCDESCUENTOTIPORIESGO }) => {
      this.updateForm(tCDESCUENTOTIPORIESGO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCDESCUENTOTIPORIESGO = this.createFromForm();
    if (tCDESCUENTOTIPORIESGO.idDescuentoTipoRiesgo !== undefined) {
      this.subscribeToSaveResponse(this.tCDESCUENTOTIPORIESGOService.update(tCDESCUENTOTIPORIESGO));
    } else {
      this.subscribeToSaveResponse(this.tCDESCUENTOTIPORIESGOService.create(tCDESCUENTOTIPORIESGO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCDESCUENTOTIPORIESGO>>): void {
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

  protected updateForm(tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO): void {
    this.editForm.patchValue({
      idDescuentoTipoRiesgo: tCDESCUENTOTIPORIESGO.idDescuentoTipoRiesgo,
      tipo: tCDESCUENTOTIPORIESGO.tipo,
      descuento: tCDESCUENTOTIPORIESGO.descuento,
    });
  }

  protected createFromForm(): ITCDESCUENTOTIPORIESGO {
    return {
      ...new TCDESCUENTOTIPORIESGO(),
      idDescuentoTipoRiesgo: this.editForm.get(['idDescuentoTipoRiesgo'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
      descuento: this.editForm.get(['descuento'])!.value,
    };
  }
}
