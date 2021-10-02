import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCPRIMATARIFA, TCPRIMATARIFA } from '../tcprimatarifa.model';
import { TCPRIMATARIFAService } from '../service/tcprimatarifa.service';

@Component({
  selector: 'jhi-tcprimatarifa-update',
  templateUrl: './tcprimatarifa-update.component.html',
})
export class TCPRIMATARIFAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idPrimaTarifa: [],
    divPrimaTarifa: [null, [Validators.required]],
    zNeta: [null, [Validators.required]],
    divPrimaRiesgo: [null, [Validators.required]],
    zRiesgo: [null, [Validators.required]],
  });

  constructor(protected tCPRIMATARIFAService: TCPRIMATARIFAService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCPRIMATARIFA }) => {
      this.updateForm(tCPRIMATARIFA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCPRIMATARIFA = this.createFromForm();
    if (tCPRIMATARIFA.idPrimaTarifa !== undefined) {
      this.subscribeToSaveResponse(this.tCPRIMATARIFAService.update(tCPRIMATARIFA));
    } else {
      this.subscribeToSaveResponse(this.tCPRIMATARIFAService.create(tCPRIMATARIFA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCPRIMATARIFA>>): void {
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

  protected updateForm(tCPRIMATARIFA: ITCPRIMATARIFA): void {
    this.editForm.patchValue({
      idPrimaTarifa: tCPRIMATARIFA.idPrimaTarifa,
      divPrimaTarifa: tCPRIMATARIFA.divPrimaTarifa,
      zNeta: tCPRIMATARIFA.zNeta,
      divPrimaRiesgo: tCPRIMATARIFA.divPrimaRiesgo,
      zRiesgo: tCPRIMATARIFA.zRiesgo,
    });
  }

  protected createFromForm(): ITCPRIMATARIFA {
    return {
      ...new TCPRIMATARIFA(),
      idPrimaTarifa: this.editForm.get(['idPrimaTarifa'])!.value,
      divPrimaTarifa: this.editForm.get(['divPrimaTarifa'])!.value,
      zNeta: this.editForm.get(['zNeta'])!.value,
      divPrimaRiesgo: this.editForm.get(['divPrimaRiesgo'])!.value,
      zRiesgo: this.editForm.get(['zRiesgo'])!.value,
    };
  }
}
