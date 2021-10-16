import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITMCOTIZACIONEXPPROPIA, TMCOTIZACIONEXPPROPIA } from '../tmcotizacionexppropia.model';
import { TMCOTIZACIONEXPPROPIAService } from '../service/tmcotizacionexppropia.service';

@Component({
  selector: 'jhi-tmcotizacionexppropia-update',
  templateUrl: './tmcotizacionexppropia-update.component.html',
})
export class TMCOTIZACIONEXPPROPIAUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idCotizacionExpPropia: [],
    numero: [null, [Validators.required]],
    periodo: [null, [Validators.required]],
    siniestro: [null, [Validators.required]],
    asegurados: [null, [Validators.required]],
    valorQx: [null, [Validators.required]],
  });

  constructor(
    protected tMCOTIZACIONEXPPROPIAService: TMCOTIZACIONEXPPROPIAService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMCOTIZACIONEXPPROPIA }) => {
      this.updateForm(tMCOTIZACIONEXPPROPIA);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tMCOTIZACIONEXPPROPIA = this.createFromForm();
    if (tMCOTIZACIONEXPPROPIA.idCotizacionExpPropia !== undefined) {
      this.subscribeToSaveResponse(this.tMCOTIZACIONEXPPROPIAService.update(tMCOTIZACIONEXPPROPIA));
    } else {
      this.subscribeToSaveResponse(this.tMCOTIZACIONEXPPROPIAService.create(tMCOTIZACIONEXPPROPIA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITMCOTIZACIONEXPPROPIA>>): void {
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

  protected updateForm(tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA): void {
    this.editForm.patchValue({
      idCotizacionExpPropia: tMCOTIZACIONEXPPROPIA.idCotizacionExpPropia,
      numero: tMCOTIZACIONEXPPROPIA.numero,
      periodo: tMCOTIZACIONEXPPROPIA.periodo,
      siniestro: tMCOTIZACIONEXPPROPIA.siniestro,
      asegurados: tMCOTIZACIONEXPPROPIA.asegurados,
      valorQx: tMCOTIZACIONEXPPROPIA.valorQx,
    });
  }

  protected createFromForm(): ITMCOTIZACIONEXPPROPIA {
    return {
      ...new TMCOTIZACIONEXPPROPIA(),
      idCotizacionExpPropia: this.editForm.get(['idCotizacionExpPropia'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      periodo: this.editForm.get(['periodo'])!.value,
      siniestro: this.editForm.get(['siniestro'])!.value,
      asegurados: this.editForm.get(['asegurados'])!.value,
      valorQx: this.editForm.get(['valorQx'])!.value,
    };
  }
}
