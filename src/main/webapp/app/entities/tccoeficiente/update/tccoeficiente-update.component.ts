import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCOEFICIENTE, TCCOEFICIENTE } from '../tccoeficiente.model';
import { TCCOEFICIENTEService } from '../service/tccoeficiente.service';

@Component({
  selector: 'jhi-tccoeficiente-update',
  templateUrl: './tccoeficiente-update.component.html',
})
export class TCCOEFICIENTEUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCoeficiente: [],
    coeficiente: [null, [Validators.required]],
    intervaloMin: [null, [Validators.required]],
    intervaloMax: [null, [Validators.required]],
    descuentoExtra: [null, [Validators.required]],
  });

  constructor(protected tCCOEFICIENTEService: TCCOEFICIENTEService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCOEFICIENTE }) => {
      this.updateForm(tCCOEFICIENTE);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCOEFICIENTE = this.createFromForm();
    if (tCCOEFICIENTE.idCoeficiente !== undefined) {
      this.subscribeToSaveResponse(this.tCCOEFICIENTEService.update(tCCOEFICIENTE));
    } else {
      this.subscribeToSaveResponse(this.tCCOEFICIENTEService.create(tCCOEFICIENTE));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCOEFICIENTE>>): void {
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

  protected updateForm(tCCOEFICIENTE: ITCCOEFICIENTE): void {
    this.editForm.patchValue({
      idCoeficiente: tCCOEFICIENTE.idCoeficiente,
      coeficiente: tCCOEFICIENTE.coeficiente,
      intervaloMin: tCCOEFICIENTE.intervaloMin,
      intervaloMax: tCCOEFICIENTE.intervaloMax,
      descuentoExtra: tCCOEFICIENTE.descuentoExtra,
    });
  }

  protected createFromForm(): ITCCOEFICIENTE {
    return {
      ...new TCCOEFICIENTE(),
      idCoeficiente: this.editForm.get(['idCoeficiente'])!.value,
      coeficiente: this.editForm.get(['coeficiente'])!.value,
      intervaloMin: this.editForm.get(['intervaloMin'])!.value,
      intervaloMax: this.editForm.get(['intervaloMax'])!.value,
      descuentoExtra: this.editForm.get(['descuentoExtra'])!.value,
    };
  }
}
