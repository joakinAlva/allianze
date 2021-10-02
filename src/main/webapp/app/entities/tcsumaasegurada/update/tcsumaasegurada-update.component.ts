import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCSUMAASEGURADA, TCSUMAASEGURADA } from '../tcsumaasegurada.model';
import { TCSUMAASEGURADAService } from '../service/tcsumaasegurada.service';

@Component({
  selector: 'jhi-tcsumaasegurada-update',
  templateUrl: './tcsumaasegurada-update.component.html',
})
export class TCSUMAASEGURADAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idSumaAsegurada: [],
    sagff: [null, [Validators.required]],
    saca: [null, [Validators.required]],
    sage: [null, [Validators.required]],
    saiqa: [null, [Validators.required]],
    saiqv: [null, [Validators.required]],
  });

  constructor(
    protected tCSUMAASEGURADAService: TCSUMAASEGURADAService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCSUMAASEGURADA }) => {
      this.updateForm(tCSUMAASEGURADA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCSUMAASEGURADA = this.createFromForm();
    if (tCSUMAASEGURADA.idSumaAsegurada !== undefined) {
      this.subscribeToSaveResponse(this.tCSUMAASEGURADAService.update(tCSUMAASEGURADA));
    } else {
      this.subscribeToSaveResponse(this.tCSUMAASEGURADAService.create(tCSUMAASEGURADA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCSUMAASEGURADA>>): void {
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

  protected updateForm(tCSUMAASEGURADA: ITCSUMAASEGURADA): void {
    this.editForm.patchValue({
      idSumaAsegurada: tCSUMAASEGURADA.idSumaAsegurada,
      sagff: tCSUMAASEGURADA.sagff,
      saca: tCSUMAASEGURADA.saca,
      sage: tCSUMAASEGURADA.sage,
      saiqa: tCSUMAASEGURADA.saiqa,
      saiqv: tCSUMAASEGURADA.saiqv,
    });
  }

  protected createFromForm(): ITCSUMAASEGURADA {
    return {
      ...new TCSUMAASEGURADA(),
      idSumaAsegurada: this.editForm.get(['idSumaAsegurada'])!.value,
      sagff: this.editForm.get(['sagff'])!.value,
      saca: this.editForm.get(['saca'])!.value,
      sage: this.editForm.get(['sage'])!.value,
      saiqa: this.editForm.get(['saiqa'])!.value,
      saiqv: this.editForm.get(['saiqv'])!.value,
    };
  }
}
