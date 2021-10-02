import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCTIPOPRODUCTO, TCTIPOPRODUCTO } from '../tctipoproducto.model';
import { TCTIPOPRODUCTOService } from '../service/tctipoproducto.service';

@Component({
  selector: 'jhi-tctipoproducto-update',
  templateUrl: './tctipoproducto-update.component.html',
})
export class TCTIPOPRODUCTOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idTipoProducto: [],
    tipoProducto: [null, [Validators.required]],
    registro: [null, [Validators.required]],
    fecha: [null, [Validators.required]],
  });

  constructor(
    protected tCTIPOPRODUCTOService: TCTIPOPRODUCTOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCTIPOPRODUCTO }) => {
      this.updateForm(tCTIPOPRODUCTO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCTIPOPRODUCTO = this.createFromForm();
    if (tCTIPOPRODUCTO.idTipoProducto !== undefined) {
      this.subscribeToSaveResponse(this.tCTIPOPRODUCTOService.update(tCTIPOPRODUCTO));
    } else {
      this.subscribeToSaveResponse(this.tCTIPOPRODUCTOService.create(tCTIPOPRODUCTO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCTIPOPRODUCTO>>): void {
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

  protected updateForm(tCTIPOPRODUCTO: ITCTIPOPRODUCTO): void {
    this.editForm.patchValue({
      idTipoProducto: tCTIPOPRODUCTO.idTipoProducto,
      tipoProducto: tCTIPOPRODUCTO.tipoProducto,
      registro: tCTIPOPRODUCTO.registro,
      fecha: tCTIPOPRODUCTO.fecha,
    });
  }

  protected createFromForm(): ITCTIPOPRODUCTO {
    return {
      ...new TCTIPOPRODUCTO(),
      idTipoProducto: this.editForm.get(['idTipoProducto'])!.value,
      tipoProducto: this.editForm.get(['tipoProducto'])!.value,
      registro: this.editForm.get(['registro'])!.value,
      fecha: this.editForm.get(['fecha'])!.value,
    };
  }
}
