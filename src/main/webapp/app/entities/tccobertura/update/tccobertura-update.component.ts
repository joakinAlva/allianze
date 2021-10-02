import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCOBERTURA, TCCOBERTURA } from '../tccobertura.model';
import { TCCOBERTURAService } from '../service/tccobertura.service';

@Component({
  selector: 'jhi-tccobertura-update',
  templateUrl: './tccobertura-update.component.html',
})
export class TCCOBERTURAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCobertura: [],
    cobertura: [null, [Validators.required]],
    posicion: [null, [Validators.required]],
  });

  constructor(protected tCCOBERTURAService: TCCOBERTURAService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCOBERTURA }) => {
      this.updateForm(tCCOBERTURA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCOBERTURA = this.createFromForm();
    if (tCCOBERTURA.idCobertura !== undefined) {
      this.subscribeToSaveResponse(this.tCCOBERTURAService.update(tCCOBERTURA));
    } else {
      this.subscribeToSaveResponse(this.tCCOBERTURAService.create(tCCOBERTURA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCOBERTURA>>): void {
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

  protected updateForm(tCCOBERTURA: ITCCOBERTURA): void {
    this.editForm.patchValue({
      idCobertura: tCCOBERTURA.idCobertura,
      cobertura: tCCOBERTURA.cobertura,
      posicion: tCCOBERTURA.posicion,
    });
  }

  protected createFromForm(): ITCCOBERTURA {
    return {
      ...new TCCOBERTURA(),
      idCobertura: this.editForm.get(['idCobertura'])!.value,
      cobertura: this.editForm.get(['cobertura'])!.value,
      posicion: this.editForm.get(['posicion'])!.value,
    };
  }
}
