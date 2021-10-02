import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCUOTAPROPUESTA, TCCUOTAPROPUESTA } from '../tccuotapropuesta.model';
import { TCCUOTAPROPUESTAService } from '../service/tccuotapropuesta.service';

@Component({
  selector: 'jhi-tccuotapropuesta-update',
  templateUrl: './tccuotapropuesta-update.component.html',
})
export class TCCUOTAPROPUESTAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCuotaPropuesta: [],
    edad: [null, [Validators.required]],
    valorVg: [null, [Validators.required]],
    valorBipTres: [null, [Validators.required]],
    valorBit: [null, [Validators.required]],
    valorDi: [null, [Validators.required]],
  });

  constructor(
    protected tCCUOTAPROPUESTAService: TCCUOTAPROPUESTAService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCUOTAPROPUESTA }) => {
      this.updateForm(tCCUOTAPROPUESTA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCUOTAPROPUESTA = this.createFromForm();
    if (tCCUOTAPROPUESTA.idCuotaPropuesta !== undefined) {
      this.subscribeToSaveResponse(this.tCCUOTAPROPUESTAService.update(tCCUOTAPROPUESTA));
    } else {
      this.subscribeToSaveResponse(this.tCCUOTAPROPUESTAService.create(tCCUOTAPROPUESTA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCUOTAPROPUESTA>>): void {
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

  protected updateForm(tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA): void {
    this.editForm.patchValue({
      idCuotaPropuesta: tCCUOTAPROPUESTA.idCuotaPropuesta,
      edad: tCCUOTAPROPUESTA.edad,
      valorVg: tCCUOTAPROPUESTA.valorVg,
      valorBipTres: tCCUOTAPROPUESTA.valorBipTres,
      valorBit: tCCUOTAPROPUESTA.valorBit,
      valorDi: tCCUOTAPROPUESTA.valorDi,
    });
  }

  protected createFromForm(): ITCCUOTAPROPUESTA {
    return {
      ...new TCCUOTAPROPUESTA(),
      idCuotaPropuesta: this.editForm.get(['idCuotaPropuesta'])!.value,
      edad: this.editForm.get(['edad'])!.value,
      valorVg: this.editForm.get(['valorVg'])!.value,
      valorBipTres: this.editForm.get(['valorBipTres'])!.value,
      valorBit: this.editForm.get(['valorBit'])!.value,
      valorDi: this.editForm.get(['valorDi'])!.value,
    };
  }
}
