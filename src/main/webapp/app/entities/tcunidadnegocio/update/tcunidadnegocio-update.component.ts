import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCUNIDADNEGOCIO, TCUNIDADNEGOCIO } from '../tcunidadnegocio.model';
import { TCUNIDADNEGOCIOService } from '../service/tcunidadnegocio.service';

@Component({
  selector: 'jhi-tcunidadnegocio-update',
  templateUrl: './tcunidadnegocio-update.component.html',
})
export class TCUNIDADNEGOCIOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idUnidadNegocio: [],
    unidadNegocio: [null, [Validators.required]],
  });

  constructor(
    protected tCUNIDADNEGOCIOService: TCUNIDADNEGOCIOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCUNIDADNEGOCIO }) => {
      this.updateForm(tCUNIDADNEGOCIO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCUNIDADNEGOCIO = this.createFromForm();
    if (tCUNIDADNEGOCIO.idUnidadNegocio !== undefined) {
      this.subscribeToSaveResponse(this.tCUNIDADNEGOCIOService.update(tCUNIDADNEGOCIO));
    } else {
      this.subscribeToSaveResponse(this.tCUNIDADNEGOCIOService.create(tCUNIDADNEGOCIO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCUNIDADNEGOCIO>>): void {
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

  protected updateForm(tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO): void {
    this.editForm.patchValue({
      idUnidadNegocio: tCUNIDADNEGOCIO.idUnidadNegocio,
      unidadNegocio: tCUNIDADNEGOCIO.unidadNegocio,
    });
  }

  protected createFromForm(): ITCUNIDADNEGOCIO {
    return {
      ...new TCUNIDADNEGOCIO(),
      idUnidadNegocio: this.editForm.get(['idUnidadNegocio'])!.value,
      unidadNegocio: this.editForm.get(['unidadNegocio'])!.value,
    };
  }
}
