import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITCCONCEPTO, TCCONCEPTO } from '../tcconcepto.model';
import { TCCONCEPTOService } from '../service/tcconcepto.service';

@Component({
  selector: 'jhi-tcconcepto-update',
  templateUrl: './tcconcepto-update.component.html',
})
export class TCCONCEPTOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    idConcepto: [],
    concepto: [null, [Validators.required]],
    dato: [null, [Validators.required]],
  });

  constructor(protected tCCONCEPTOService: TCCONCEPTOService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCONCEPTO }) => {
      this.updateForm(tCCONCEPTO);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tCCONCEPTO = this.createFromForm();
    if (tCCONCEPTO.idConcepto !== undefined) {
      this.subscribeToSaveResponse(this.tCCONCEPTOService.update(tCCONCEPTO));
    } else {
      this.subscribeToSaveResponse(this.tCCONCEPTOService.create(tCCONCEPTO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITCCONCEPTO>>): void {
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

  protected updateForm(tCCONCEPTO: ITCCONCEPTO): void {
    this.editForm.patchValue({
      idConcepto: tCCONCEPTO.idConcepto,
      concepto: tCCONCEPTO.concepto,
      dato: tCCONCEPTO.dato,
    });
  }

  protected createFromForm(): ITCCONCEPTO {
    return {
      ...new TCCONCEPTO(),
      idConcepto: this.editForm.get(['idConcepto'])!.value,
      concepto: this.editForm.get(['concepto'])!.value,
      dato: this.editForm.get(['dato'])!.value,
    };
  }
}
