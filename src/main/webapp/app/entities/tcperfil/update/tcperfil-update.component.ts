import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCPERFIL, TCPERFIL } from '../tcperfil.model';
import { TCPERFILService } from '../service/tcperfil.service';

@Component({
  selector: 'jhi-tcperfil-update',
  templateUrl: './tcperfil-update.component.html',
})
export class TCPERFILUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idPerfil: [],
    perfil: [null, [Validators.required]],
  });

  constructor(protected tCPERFILService: TCPERFILService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCPERFIL }) => {
      this.updateForm(tCPERFIL);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCPERFIL = this.createFromForm();
    if (tCPERFIL.idPerfil !== undefined) {
      this.subscribeToSaveResponse(this.tCPERFILService.update(tCPERFIL));
    } else {
      this.subscribeToSaveResponse(this.tCPERFILService.create(tCPERFIL));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCPERFIL>>): void {
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

  protected updateForm(tCPERFIL: ITCPERFIL): void {
    this.editForm.patchValue({
      idPerfil: tCPERFIL.idPerfil,
      perfil: tCPERFIL.perfil,
    });
  }

  protected createFromForm(): ITCPERFIL {
    return {
      ...new TCPERFIL(),
      idPerfil: this.editForm.get(['idPerfil'])!.value,
      perfil: this.editForm.get(['perfil'])!.value,
    };
  }
}
