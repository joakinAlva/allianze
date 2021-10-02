import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCREGIONAL, TCREGIONAL } from '../tcregional.model';
import { TCREGIONALService } from '../service/tcregional.service';

@Component({
  selector: 'jhi-tcregional-update',
  templateUrl: './tcregional-update.component.html',
})
export class TCREGIONALUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idRegional: [],
    regional: [null, [Validators.required]],
  });

  constructor(protected tCREGIONALService: TCREGIONALService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCREGIONAL }) => {
      this.updateForm(tCREGIONAL);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCREGIONAL = this.createFromForm();
    if (tCREGIONAL.idRegional !== undefined) {
      this.subscribeToSaveResponse(this.tCREGIONALService.update(tCREGIONAL));
    } else {
      this.subscribeToSaveResponse(this.tCREGIONALService.create(tCREGIONAL));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCREGIONAL>>): void {
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

  protected updateForm(tCREGIONAL: ITCREGIONAL): void {
    this.editForm.patchValue({
      idRegional: tCREGIONAL.idRegional,
      regional: tCREGIONAL.regional,
    });
  }

  protected createFromForm(): ITCREGIONAL {
    return {
      ...new TCREGIONAL(),
      idRegional: this.editForm.get(['idRegional'])!.value,
      regional: this.editForm.get(['regional'])!.value,
    };
  }
}
