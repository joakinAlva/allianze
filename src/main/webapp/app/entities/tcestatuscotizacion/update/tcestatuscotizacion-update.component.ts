import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCESTATUSCOTIZACION, TCESTATUSCOTIZACION } from '../tcestatuscotizacion.model';
import { TCESTATUSCOTIZACIONService } from '../service/tcestatuscotizacion.service';

@Component({
  selector: 'jhi-tcestatuscotizacion-update',
  templateUrl: './tcestatuscotizacion-update.component.html',
})
export class TCESTATUSCOTIZACIONUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idEstatusCotizacion: [],
    orden: [null, [Validators.required]],
    estatusCotizacion: [null, [Validators.required]],
  });

  constructor(
    protected tCESTATUSCOTIZACIONService: TCESTATUSCOTIZACIONService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCESTATUSCOTIZACION }) => {
      this.updateForm(tCESTATUSCOTIZACION);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCESTATUSCOTIZACION = this.createFromForm();
    if (tCESTATUSCOTIZACION.idEstatusCotizacion !== undefined) {
      this.subscribeToSaveResponse(this.tCESTATUSCOTIZACIONService.update(tCESTATUSCOTIZACION));
    } else {
      this.subscribeToSaveResponse(this.tCESTATUSCOTIZACIONService.create(tCESTATUSCOTIZACION));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCESTATUSCOTIZACION>>): void {
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

  protected updateForm(tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION): void {
    this.editForm.patchValue({
      idEstatusCotizacion: tCESTATUSCOTIZACION.idEstatusCotizacion,
      orden: tCESTATUSCOTIZACION.orden,
      estatusCotizacion: tCESTATUSCOTIZACION.estatusCotizacion,
    });
  }

  protected createFromForm(): ITCESTATUSCOTIZACION {
    return {
      ...new TCESTATUSCOTIZACION(),
      idEstatusCotizacion: this.editForm.get(['idEstatusCotizacion'])!.value,
      orden: this.editForm.get(['orden'])!.value,
      estatusCotizacion: this.editForm.get(['estatusCotizacion'])!.value,
    };
  }
}
