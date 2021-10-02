import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCRANGOPRIMA, TCRANGOPRIMA } from '../tcrangoprima.model';
import { TCRANGOPRIMAService } from '../service/tcrangoprima.service';

@Component({
  selector: 'jhi-tcrangoprima-update',
  templateUrl: './tcrangoprima-update.component.html',
})
export class TCRANGOPRIMAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idRangoPrima: [],
    valorMin: [null, [Validators.required]],
    valorMax: [null, [Validators.required]],
    dividendos: [null, [Validators.required]],
    comision: [null, [Validators.required]],
  });

  constructor(protected tCRANGOPRIMAService: TCRANGOPRIMAService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCRANGOPRIMA }) => {
      this.updateForm(tCRANGOPRIMA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCRANGOPRIMA = this.createFromForm();
    if (tCRANGOPRIMA.idRangoPrima !== undefined) {
      this.subscribeToSaveResponse(this.tCRANGOPRIMAService.update(tCRANGOPRIMA));
    } else {
      this.subscribeToSaveResponse(this.tCRANGOPRIMAService.create(tCRANGOPRIMA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCRANGOPRIMA>>): void {
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

  protected updateForm(tCRANGOPRIMA: ITCRANGOPRIMA): void {
    this.editForm.patchValue({
      idRangoPrima: tCRANGOPRIMA.idRangoPrima,
      valorMin: tCRANGOPRIMA.valorMin,
      valorMax: tCRANGOPRIMA.valorMax,
      dividendos: tCRANGOPRIMA.dividendos,
      comision: tCRANGOPRIMA.comision,
    });
  }

  protected createFromForm(): ITCRANGOPRIMA {
    return {
      ...new TCRANGOPRIMA(),
      idRangoPrima: this.editForm.get(['idRangoPrima'])!.value,
      valorMin: this.editForm.get(['valorMin'])!.value,
      valorMax: this.editForm.get(['valorMax'])!.value,
      dividendos: this.editForm.get(['dividendos'])!.value,
      comision: this.editForm.get(['comision'])!.value,
    };
  }
}
