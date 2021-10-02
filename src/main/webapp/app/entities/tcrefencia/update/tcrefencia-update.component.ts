import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCREFENCIA, TCREFENCIA } from '../tcrefencia.model';
import { TCREFENCIAService } from '../service/tcrefencia.service';

@Component({
  selector: 'jhi-tcrefencia-update',
  templateUrl: './tcrefencia-update.component.html',
})
export class TCREFENCIAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idReferencia: [],
    periodo: [null, [Validators.required]],
    referencia: [null, [Validators.required]],
    edadpromedio: [null, [Validators.required]],
    cuota: [null, [Validators.required]],
  });

  constructor(protected tCREFENCIAService: TCREFENCIAService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCREFENCIA }) => {
      this.updateForm(tCREFENCIA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCREFENCIA = this.createFromForm();
    if (tCREFENCIA.idReferencia !== undefined) {
      this.subscribeToSaveResponse(this.tCREFENCIAService.update(tCREFENCIA));
    } else {
      this.subscribeToSaveResponse(this.tCREFENCIAService.create(tCREFENCIA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCREFENCIA>>): void {
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

  protected updateForm(tCREFENCIA: ITCREFENCIA): void {
    this.editForm.patchValue({
      idReferencia: tCREFENCIA.idReferencia,
      periodo: tCREFENCIA.periodo,
      referencia: tCREFENCIA.referencia,
      edadpromedio: tCREFENCIA.edadpromedio,
      cuota: tCREFENCIA.cuota,
    });
  }

  protected createFromForm(): ITCREFENCIA {
    return {
      ...new TCREFENCIA(),
      idReferencia: this.editForm.get(['idReferencia'])!.value,
      periodo: this.editForm.get(['periodo'])!.value,
      referencia: this.editForm.get(['referencia'])!.value,
      edadpromedio: this.editForm.get(['edadpromedio'])!.value,
      cuota: this.editForm.get(['cuota'])!.value,
    };
  }
}
