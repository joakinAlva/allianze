import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCTIPO, TCTIPO } from '../tctipo.model';
import { TCTIPOService } from '../service/tctipo.service';

@Component({
  selector: 'jhi-tctipo-update',
  templateUrl: './tctipo-update.component.html',
})
export class TCTIPOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idTipo: [],
    tipoRegla: [null, [Validators.required]],
  });

  constructor(protected tCTIPOService: TCTIPOService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCTIPO }) => {
      this.updateForm(tCTIPO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCTIPO = this.createFromForm();
    if (tCTIPO.idTipo !== undefined) {
      this.subscribeToSaveResponse(this.tCTIPOService.update(tCTIPO));
    } else {
      this.subscribeToSaveResponse(this.tCTIPOService.create(tCTIPO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCTIPO>>): void {
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

  protected updateForm(tCTIPO: ITCTIPO): void {
    this.editForm.patchValue({
      idTipo: tCTIPO.idTipo,
      tipoRegla: tCTIPO.tipoRegla,
    });
  }

  protected createFromForm(): ITCTIPO {
    return {
      ...new TCTIPO(),
      idTipo: this.editForm.get(['idTipo'])!.value,
      tipoRegla: this.editForm.get(['tipoRegla'])!.value,
    };
  }
}
