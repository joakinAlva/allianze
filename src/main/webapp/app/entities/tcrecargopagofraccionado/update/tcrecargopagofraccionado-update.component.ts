import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCRECARGOPAGOFRACCIONADO, TCRECARGOPAGOFRACCIONADO } from '../tcrecargopagofraccionado.model';
import { TCRECARGOPAGOFRACCIONADOService } from '../service/tcrecargopagofraccionado.service';

@Component({
  selector: 'jhi-tcrecargopagofraccionado-update',
  templateUrl: './tcrecargopagofraccionado-update.component.html',
})
export class TCRECARGOPAGOFRACCIONADOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idRecargoPagoFraccionado: [],
    periodo: [null, [Validators.required]],
    porcentaje: [null, [Validators.required]],
  });

  constructor(
    protected tCRECARGOPAGOFRACCIONADOService: TCRECARGOPAGOFRACCIONADOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCRECARGOPAGOFRACCIONADO }) => {
      this.updateForm(tCRECARGOPAGOFRACCIONADO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCRECARGOPAGOFRACCIONADO = this.createFromForm();
    if (tCRECARGOPAGOFRACCIONADO.idRecargoPagoFraccionado !== undefined) {
      this.subscribeToSaveResponse(this.tCRECARGOPAGOFRACCIONADOService.update(tCRECARGOPAGOFRACCIONADO));
    } else {
      this.subscribeToSaveResponse(this.tCRECARGOPAGOFRACCIONADOService.create(tCRECARGOPAGOFRACCIONADO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCRECARGOPAGOFRACCIONADO>>): void {
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

  protected updateForm(tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO): void {
    this.editForm.patchValue({
      idRecargoPagoFraccionado: tCRECARGOPAGOFRACCIONADO.idRecargoPagoFraccionado,
      periodo: tCRECARGOPAGOFRACCIONADO.periodo,
      porcentaje: tCRECARGOPAGOFRACCIONADO.porcentaje,
    });
  }

  protected createFromForm(): ITCRECARGOPAGOFRACCIONADO {
    return {
      ...new TCRECARGOPAGOFRACCIONADO(),
      idRecargoPagoFraccionado: this.editForm.get(['idRecargoPagoFraccionado'])!.value,
      periodo: this.editForm.get(['periodo'])!.value,
      porcentaje: this.editForm.get(['porcentaje'])!.value,
    };
  }
}
