import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCSUBGRUPO, TCSUBGRUPO } from '../tcsubgrupo.model';
import { TCSUBGRUPOService } from '../service/tcsubgrupo.service';

@Component({
  selector: 'jhi-tcsubgrupo-update',
  templateUrl: './tcsubgrupo-update.component.html',
})
export class TCSUBGRUPOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idSubGrupo: [],
    subGrupo: [null, [Validators.required]],
  });

  constructor(protected tCSUBGRUPOService: TCSUBGRUPOService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCSUBGRUPO }) => {
      this.updateForm(tCSUBGRUPO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCSUBGRUPO = this.createFromForm();
    if (tCSUBGRUPO.idSubGrupo !== undefined) {
      this.subscribeToSaveResponse(this.tCSUBGRUPOService.update(tCSUBGRUPO));
    } else {
      this.subscribeToSaveResponse(this.tCSUBGRUPOService.create(tCSUBGRUPO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCSUBGRUPO>>): void {
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

  protected updateForm(tCSUBGRUPO: ITCSUBGRUPO): void {
    this.editForm.patchValue({
      idSubGrupo: tCSUBGRUPO.idSubGrupo,
      subGrupo: tCSUBGRUPO.subGrupo,
    });
  }

  protected createFromForm(): ITCSUBGRUPO {
    return {
      ...new TCSUBGRUPO(),
      idSubGrupo: this.editForm.get(['idSubGrupo'])!.value,
      subGrupo: this.editForm.get(['subGrupo'])!.value,
    };
  }
}
