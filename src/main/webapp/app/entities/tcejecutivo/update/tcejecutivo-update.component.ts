import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCEJECUTIVO, TCEJECUTIVO } from '../tcejecutivo.model';
import { TCEJECUTIVOService } from '../service/tcejecutivo.service';

@Component({
  selector: 'jhi-tcejecutivo-update',
  templateUrl: './tcejecutivo-update.component.html',
})
export class TCEJECUTIVOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idEjecutivo: [],
    ejecutivo: [null, [Validators.required]],
  });

  constructor(protected tCEJECUTIVOService: TCEJECUTIVOService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCEJECUTIVO }) => {
      this.updateForm(tCEJECUTIVO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCEJECUTIVO = this.createFromForm();
    if (tCEJECUTIVO.idEjecutivo !== undefined) {
      this.subscribeToSaveResponse(this.tCEJECUTIVOService.update(tCEJECUTIVO));
    } else {
      this.subscribeToSaveResponse(this.tCEJECUTIVOService.create(tCEJECUTIVO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCEJECUTIVO>>): void {
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

  protected updateForm(tCEJECUTIVO: ITCEJECUTIVO): void {
    this.editForm.patchValue({
      idEjecutivo: tCEJECUTIVO.idEjecutivo,
      ejecutivo: tCEJECUTIVO.ejecutivo,
    });
  }

  protected createFromForm(): ITCEJECUTIVO {
    return {
      ...new TCEJECUTIVO(),
      idEjecutivo: this.editForm.get(['idEjecutivo'])!.value,
      ejecutivo: this.editForm.get(['ejecutivo'])!.value,
    };
  }
}
