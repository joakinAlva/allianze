import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCOCUPACION, TCOCUPACION } from '../tcocupacion.model';
import { TCOCUPACIONService } from '../service/tcocupacion.service';

@Component({
  selector: 'jhi-tcocupacion-update',
  templateUrl: './tcocupacion-update.component.html',
})
export class TCOCUPACIONUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idOcupacion: [],
    ocupacion: [null, [Validators.required]],
    factorGiroAnterior: [null, [Validators.required]],
    factorGiroActual: [null, [Validators.required]],
  });

  constructor(protected tCOCUPACIONService: TCOCUPACIONService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCOCUPACION }) => {
      this.updateForm(tCOCUPACION);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCOCUPACION = this.createFromForm();
    if (tCOCUPACION.idOcupacion !== undefined) {
      this.subscribeToSaveResponse(this.tCOCUPACIONService.update(tCOCUPACION));
    } else {
      this.subscribeToSaveResponse(this.tCOCUPACIONService.create(tCOCUPACION));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCOCUPACION>>): void {
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

  protected updateForm(tCOCUPACION: ITCOCUPACION): void {
    this.editForm.patchValue({
      idOcupacion: tCOCUPACION.idOcupacion,
      ocupacion: tCOCUPACION.ocupacion,
      factorGiroAnterior: tCOCUPACION.factorGiroAnterior,
      factorGiroActual: tCOCUPACION.factorGiroActual,
    });
  }

  protected createFromForm(): ITCOCUPACION {
    return {
      ...new TCOCUPACION(),
      idOcupacion: this.editForm.get(['idOcupacion'])!.value,
      ocupacion: this.editForm.get(['ocupacion'])!.value,
      factorGiroAnterior: this.editForm.get(['factorGiroAnterior'])!.value,
      factorGiroActual: this.editForm.get(['factorGiroActual'])!.value,
    };
  }
}
