import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ITMUSUARIO, TMUSUARIO } from '../tmusuario.model';
import { TMUSUARIOService } from '../service/tmusuario.service';
import { ITCPERFIL } from 'app/entities/tcperfil/tcperfil.model';
import { TCPERFILService } from 'app/entities/tcperfil/service/tcperfil.service';

@Component({
  selector: 'jhi-tmusuario-update',
  templateUrl: './tmusuario-update.component.html',
})
export class TMUSUARIOUpdateComponent implements OnInit {
  isSaving = false;

  tCPERFILSSharedCollection: ITCPERFIL[] = [];

  editForm = this.fb.group({
    idUsuario: [],
    nombre: [null, [Validators.required]],
    apellidos: [null, [Validators.required]],
    correoElectronico: [null, [Validators.required]],
    usuario: [null, [Validators.required]],
    clave: [null, [Validators.required]],
    fechaRegistro: [null, [Validators.required]],
    token: [null, [Validators.required]],
    estatus: [null, [Validators.required, Validators.max(1)]],
    employee: [],
  });

  constructor(
    protected tMUSUARIOService: TMUSUARIOService,
    protected tCPERFILService: TCPERFILService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMUSUARIO }) => {
      this.updateForm(tMUSUARIO);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tMUSUARIO = this.createFromForm();
    if (tMUSUARIO.idUsuario !== undefined) {
      this.subscribeToSaveResponse(this.tMUSUARIOService.update(tMUSUARIO));
    } else {
      this.subscribeToSaveResponse(this.tMUSUARIOService.create(tMUSUARIO));
    }
  }

  trackTCPERFILByIdPerfil(index: number, item: ITCPERFIL): number {
    return item.idPerfil!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITMUSUARIO>>): void {
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

  protected updateForm(tMUSUARIO: ITMUSUARIO): void {
    this.editForm.patchValue({
      idUsuario: tMUSUARIO.idUsuario,
      nombre: tMUSUARIO.nombre,
      apellidos: tMUSUARIO.apellidos,
      correoElectronico: tMUSUARIO.correoElectronico,
      usuario: tMUSUARIO.usuario,
      clave: tMUSUARIO.clave,
      fechaRegistro: tMUSUARIO.fechaRegistro,
      token: tMUSUARIO.token,
      estatus: tMUSUARIO.estatus,
      employee: tMUSUARIO.employee,
    });

    this.tCPERFILSSharedCollection = this.tCPERFILService.addTCPERFILToCollectionIfMissing(
      this.tCPERFILSSharedCollection,
      tMUSUARIO.employee
    );
  }

  protected loadRelationshipsOptions(): void {
    this.tCPERFILService
      .query()
      .pipe(map((res: HttpResponse<ITCPERFIL[]>) => res.body ?? []))
      .pipe(
        map((tCPERFILS: ITCPERFIL[]) =>
          this.tCPERFILService.addTCPERFILToCollectionIfMissing(tCPERFILS, this.editForm.get('employee')!.value)
        )
      )
      .subscribe((tCPERFILS: ITCPERFIL[]) => (this.tCPERFILSSharedCollection = tCPERFILS));
  }

  protected createFromForm(): ITMUSUARIO {
    return {
      ...new TMUSUARIO(),
      idUsuario: this.editForm.get(['idUsuario'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      apellidos: this.editForm.get(['apellidos'])!.value,
      correoElectronico: this.editForm.get(['correoElectronico'])!.value,
      usuario: this.editForm.get(['usuario'])!.value,
      clave: this.editForm.get(['clave'])!.value,
      fechaRegistro: this.editForm.get(['fechaRegistro'])!.value,
      token: this.editForm.get(['token'])!.value,
      estatus: this.editForm.get(['estatus'])!.value,
      employee: this.editForm.get(['employee'])!.value,
    };
  }
}
