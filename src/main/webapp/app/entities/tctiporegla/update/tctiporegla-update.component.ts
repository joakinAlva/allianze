import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCTIPOREGLA, TCTIPOREGLA } from '../tctiporegla.model';
import { TCTIPOREGLAService } from '../service/tctiporegla.service';

@Component({
  selector: 'jhi-tctiporegla-update',
  templateUrl: './tctiporegla-update.component.html',
})
export class TCTIPOREGLAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idTipoRegla: [],
    tipo: [null, [Validators.required]],
    segmento: [null, [Validators.required]],
  });

  constructor(protected tCTIPOREGLAService: TCTIPOREGLAService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCTIPOREGLA }) => {
      this.updateForm(tCTIPOREGLA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCTIPOREGLA = this.createFromForm();
    if (tCTIPOREGLA.idTipoRegla !== undefined) {
      this.subscribeToSaveResponse(this.tCTIPOREGLAService.update(tCTIPOREGLA));
    } else {
      this.subscribeToSaveResponse(this.tCTIPOREGLAService.create(tCTIPOREGLA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCTIPOREGLA>>): void {
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

  protected updateForm(tCTIPOREGLA: ITCTIPOREGLA): void {
    this.editForm.patchValue({
      idTipoRegla: tCTIPOREGLA.idTipoRegla,
      tipo: tCTIPOREGLA.tipo,
      segmento: tCTIPOREGLA.segmento,
    });
  }

  protected createFromForm(): ITCTIPOREGLA {
    return {
      ...new TCTIPOREGLA(),
      idTipoRegla: this.editForm.get(['idTipoRegla'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
      segmento: this.editForm.get(['segmento'])!.value,
    };
  }
}
