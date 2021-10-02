import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCFACTORDESCUENTO, TCFACTORDESCUENTO } from '../tcfactordescuento.model';
import { TCFACTORDESCUENTOService } from '../service/tcfactordescuento.service';

@Component({
  selector: 'jhi-tcfactordescuento-update',
  templateUrl: './tcfactordescuento-update.component.html',
})
export class TCFACTORDESCUENTOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idFactorDescuento: [],
    factor: [null, [Validators.required]],
    porcentaje: [null, [Validators.required]],
  });

  constructor(
    protected tCFACTORDESCUENTOService: TCFACTORDESCUENTOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCFACTORDESCUENTO }) => {
      this.updateForm(tCFACTORDESCUENTO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCFACTORDESCUENTO = this.createFromForm();
    if (tCFACTORDESCUENTO.idFactorDescuento !== undefined) {
      this.subscribeToSaveResponse(this.tCFACTORDESCUENTOService.update(tCFACTORDESCUENTO));
    } else {
      this.subscribeToSaveResponse(this.tCFACTORDESCUENTOService.create(tCFACTORDESCUENTO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCFACTORDESCUENTO>>): void {
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

  protected updateForm(tCFACTORDESCUENTO: ITCFACTORDESCUENTO): void {
    this.editForm.patchValue({
      idFactorDescuento: tCFACTORDESCUENTO.idFactorDescuento,
      factor: tCFACTORDESCUENTO.factor,
      porcentaje: tCFACTORDESCUENTO.porcentaje,
    });
  }

  protected createFromForm(): ITCFACTORDESCUENTO {
    return {
      ...new TCFACTORDESCUENTO(),
      idFactorDescuento: this.editForm.get(['idFactorDescuento'])!.value,
      factor: this.editForm.get(['factor'])!.value,
      porcentaje: this.editForm.get(['porcentaje'])!.value,
    };
  }
}
