import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCPRIMANETASDESC, TCPRIMANETASDESC } from '../tcprimanetasdesc.model';
import { TCPRIMANETASDESCService } from '../service/tcprimanetasdesc.service';

@Component({
  selector: 'jhi-tcprimanetasdesc-update',
  templateUrl: './tcprimanetasdesc-update.component.html',
})
export class TCPRIMANETASDESCUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idPrimaNetaSdesc: [],
    primaNetaSdesc: [null, [Validators.required]],
    margenMin: [null, [Validators.required]],
    margenMax: [null, [Validators.required]],
    maxComSd: [null, [Validators.required]],
    maxComEp: [null, [Validators.required]],
  });

  constructor(
    protected tCPRIMANETASDESCService: TCPRIMANETASDESCService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCPRIMANETASDESC }) => {
      this.updateForm(tCPRIMANETASDESC);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCPRIMANETASDESC = this.createFromForm();
    if (tCPRIMANETASDESC.idPrimaNetaSdesc !== undefined) {
      this.subscribeToSaveResponse(this.tCPRIMANETASDESCService.update(tCPRIMANETASDESC));
    } else {
      this.subscribeToSaveResponse(this.tCPRIMANETASDESCService.create(tCPRIMANETASDESC));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCPRIMANETASDESC>>): void {
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

  protected updateForm(tCPRIMANETASDESC: ITCPRIMANETASDESC): void {
    this.editForm.patchValue({
      idPrimaNetaSdesc: tCPRIMANETASDESC.idPrimaNetaSdesc,
      primaNetaSdesc: tCPRIMANETASDESC.primaNetaSdesc,
      margenMin: tCPRIMANETASDESC.margenMin,
      margenMax: tCPRIMANETASDESC.margenMax,
      maxComSd: tCPRIMANETASDESC.maxComSd,
      maxComEp: tCPRIMANETASDESC.maxComEp,
    });
  }

  protected createFromForm(): ITCPRIMANETASDESC {
    return {
      ...new TCPRIMANETASDESC(),
      idPrimaNetaSdesc: this.editForm.get(['idPrimaNetaSdesc'])!.value,
      primaNetaSdesc: this.editForm.get(['primaNetaSdesc'])!.value,
      margenMin: this.editForm.get(['margenMin'])!.value,
      margenMax: this.editForm.get(['margenMax'])!.value,
      maxComSd: this.editForm.get(['maxComSd'])!.value,
      maxComEp: this.editForm.get(['maxComEp'])!.value,
    };
  }
}
