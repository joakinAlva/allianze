import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCHIPOTESIS, TCHIPOTESIS } from '../tchipotesis.model';
import { TCHIPOTESISService } from '../service/tchipotesis.service';

@Component({
  selector: 'jhi-tchipotesis-update',
  templateUrl: './tchipotesis-update.component.html',
})
export class TCHIPOTESISUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idHipotesis: [],
    hipotesis: [null, [Validators.required]],
    valor: [null, [Validators.required]],
    variable: [null, [Validators.required]],
  });

  constructor(protected tCHIPOTESISService: TCHIPOTESISService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCHIPOTESIS }) => {
      this.updateForm(tCHIPOTESIS);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCHIPOTESIS = this.createFromForm();
    if (tCHIPOTESIS.idHipotesis !== undefined) {
      this.subscribeToSaveResponse(this.tCHIPOTESISService.update(tCHIPOTESIS));
    } else {
      this.subscribeToSaveResponse(this.tCHIPOTESISService.create(tCHIPOTESIS));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCHIPOTESIS>>): void {
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

  protected updateForm(tCHIPOTESIS: ITCHIPOTESIS): void {
    this.editForm.patchValue({
      idHipotesis: tCHIPOTESIS.idHipotesis,
      hipotesis: tCHIPOTESIS.hipotesis,
      valor: tCHIPOTESIS.valor,
      variable: tCHIPOTESIS.variable,
    });
  }

  protected createFromForm(): ITCHIPOTESIS {
    return {
      ...new TCHIPOTESIS(),
      idHipotesis: this.editForm.get(['idHipotesis'])!.value,
      hipotesis: this.editForm.get(['hipotesis'])!.value,
      valor: this.editForm.get(['valor'])!.value,
      variable: this.editForm.get(['variable'])!.value,
    };
  }
}
