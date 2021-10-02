import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCUOTAVALOR, TCCUOTAVALOR } from '../tccuotavalor.model';
import { TCCUOTAVALORService } from '../service/tccuotavalor.service';

@Component({
  selector: 'jhi-tccuotavalor-update',
  templateUrl: './tccuotavalor-update.component.html',
})
export class TCCUOTAVALORUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCuotaValor: [],
    porcentaje: [null, [Validators.required]],
    valor: [null, [Validators.required]],
  });

  constructor(protected tCCUOTAVALORService: TCCUOTAVALORService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCUOTAVALOR }) => {
      this.updateForm(tCCUOTAVALOR);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCUOTAVALOR = this.createFromForm();
    if (tCCUOTAVALOR.idCuotaValor !== undefined) {
      this.subscribeToSaveResponse(this.tCCUOTAVALORService.update(tCCUOTAVALOR));
    } else {
      this.subscribeToSaveResponse(this.tCCUOTAVALORService.create(tCCUOTAVALOR));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCUOTAVALOR>>): void {
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

  protected updateForm(tCCUOTAVALOR: ITCCUOTAVALOR): void {
    this.editForm.patchValue({
      idCuotaValor: tCCUOTAVALOR.idCuotaValor,
      porcentaje: tCCUOTAVALOR.porcentaje,
      valor: tCCUOTAVALOR.valor,
    });
  }

  protected createFromForm(): ITCCUOTAVALOR {
    return {
      ...new TCCUOTAVALOR(),
      idCuotaValor: this.editForm.get(['idCuotaValor'])!.value,
      porcentaje: this.editForm.get(['porcentaje'])!.value,
      valor: this.editForm.get(['valor'])!.value,
    };
  }
}
