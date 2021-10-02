import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCFACTORSAMI, TCFACTORSAMI } from '../tcfactorsami.model';
import { TCFACTORSAMIService } from '../service/tcfactorsami.service';

@Component({
  selector: 'jhi-tcfactorsami-update',
  templateUrl: './tcfactorsami-update.component.html',
})
export class TCFACTORSAMIUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idFactorSami: [],
    minAsegurados: [null, [Validators.required]],
    maxAsegurados: [null, [Validators.required]],
    factor: [null, [Validators.required]],
  });

  constructor(protected tCFACTORSAMIService: TCFACTORSAMIService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCFACTORSAMI }) => {
      this.updateForm(tCFACTORSAMI);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCFACTORSAMI = this.createFromForm();
    if (tCFACTORSAMI.idFactorSami !== undefined) {
      this.subscribeToSaveResponse(this.tCFACTORSAMIService.update(tCFACTORSAMI));
    } else {
      this.subscribeToSaveResponse(this.tCFACTORSAMIService.create(tCFACTORSAMI));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCFACTORSAMI>>): void {
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

  protected updateForm(tCFACTORSAMI: ITCFACTORSAMI): void {
    this.editForm.patchValue({
      idFactorSami: tCFACTORSAMI.idFactorSami,
      minAsegurados: tCFACTORSAMI.minAsegurados,
      maxAsegurados: tCFACTORSAMI.maxAsegurados,
      factor: tCFACTORSAMI.factor,
    });
  }

  protected createFromForm(): ITCFACTORSAMI {
    return {
      ...new TCFACTORSAMI(),
      idFactorSami: this.editForm.get(['idFactorSami'])!.value,
      minAsegurados: this.editForm.get(['minAsegurados'])!.value,
      maxAsegurados: this.editForm.get(['maxAsegurados'])!.value,
      factor: this.editForm.get(['factor'])!.value,
    };
  }
}
