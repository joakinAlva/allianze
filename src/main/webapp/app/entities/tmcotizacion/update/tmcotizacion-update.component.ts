import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITMCOTIZACION, TMCOTIZACION } from '../tmcotizacion.model';
import { TMCOTIZACIONService } from '../service/tmcotizacion.service';

@Component({
  selector: 'jhi-tmcotizacion-update',
  templateUrl: './tmcotizacion-update.component.html',
})
export class TMCOTIZACIONUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCotizacion: [],
    usuario: [null, [Validators.required]],
    fechaRegistro: [null, [Validators.required]],
    estatus: [null, [Validators.required]],
    eliminada: [null, [Validators.required]],
  });

  constructor(protected tMCOTIZACIONService: TMCOTIZACIONService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMCOTIZACION }) => {
      this.updateForm(tMCOTIZACION);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tMCOTIZACION = this.createFromForm();
    if (tMCOTIZACION.idCotizacion !== undefined) {
      this.subscribeToSaveResponse(this.tMCOTIZACIONService.update(tMCOTIZACION));
    } else {
      this.subscribeToSaveResponse(this.tMCOTIZACIONService.create(tMCOTIZACION));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITMCOTIZACION>>): void {
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

  protected updateForm(tMCOTIZACION: ITMCOTIZACION): void {
    this.editForm.patchValue({
      idCotizacion: tMCOTIZACION.idCotizacion,
      usuario: tMCOTIZACION.usuario,
      fechaRegistro: tMCOTIZACION.fechaRegistro,
      estatus: tMCOTIZACION.estatus,
      eliminada: tMCOTIZACION.eliminada,
    });
  }

  protected createFromForm(): ITMCOTIZACION {
    return {
      ...new TMCOTIZACION(),
      idCotizacion: this.editForm.get(['idCotizacion'])!.value,
      usuario: this.editForm.get(['usuario'])!.value,
      fechaRegistro: this.editForm.get(['fechaRegistro'])!.value,
      estatus: this.editForm.get(['estatus'])!.value,
      eliminada: this.editForm.get(['eliminada'])!.value,
    };
  }
}
