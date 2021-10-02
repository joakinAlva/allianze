import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCDESCUENTOSUMAASEGURADA, TCDESCUENTOSUMAASEGURADA } from '../tcdescuentosumaasegurada.model';
import { TCDESCUENTOSUMAASEGURADAService } from '../service/tcdescuentosumaasegurada.service';

@Component({
  selector: 'jhi-tcdescuentosumaasegurada-update',
  templateUrl: './tcdescuentosumaasegurada-update.component.html',
})
export class TCDESCUENTOSUMAASEGURADAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idDescuentoSumaAsegurada: [],
    minSuma: [null, [Validators.required]],
    maxSuma: [null, [Validators.required]],
    porcentaje: [null, [Validators.required]],
  });

  constructor(
    protected tCDESCUENTOSUMAASEGURADAService: TCDESCUENTOSUMAASEGURADAService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCDESCUENTOSUMAASEGURADA }) => {
      this.updateForm(tCDESCUENTOSUMAASEGURADA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCDESCUENTOSUMAASEGURADA = this.createFromForm();
    if (tCDESCUENTOSUMAASEGURADA.idDescuentoSumaAsegurada !== undefined) {
      this.subscribeToSaveResponse(this.tCDESCUENTOSUMAASEGURADAService.update(tCDESCUENTOSUMAASEGURADA));
    } else {
      this.subscribeToSaveResponse(this.tCDESCUENTOSUMAASEGURADAService.create(tCDESCUENTOSUMAASEGURADA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCDESCUENTOSUMAASEGURADA>>): void {
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

  protected updateForm(tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA): void {
    this.editForm.patchValue({
      idDescuentoSumaAsegurada: tCDESCUENTOSUMAASEGURADA.idDescuentoSumaAsegurada,
      minSuma: tCDESCUENTOSUMAASEGURADA.minSuma,
      maxSuma: tCDESCUENTOSUMAASEGURADA.maxSuma,
      porcentaje: tCDESCUENTOSUMAASEGURADA.porcentaje,
    });
  }

  protected createFromForm(): ITCDESCUENTOSUMAASEGURADA {
    return {
      ...new TCDESCUENTOSUMAASEGURADA(),
      idDescuentoSumaAsegurada: this.editForm.get(['idDescuentoSumaAsegurada'])!.value,
      minSuma: this.editForm.get(['minSuma'])!.value,
      maxSuma: this.editForm.get(['maxSuma'])!.value,
      porcentaje: this.editForm.get(['porcentaje'])!.value,
    };
  }
}
