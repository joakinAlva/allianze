import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCFACTORDESCUENTO } from '../tcfactordescuento.model';

@Component({
  selector: 'jhi-tcfactordescuento-detail',
  templateUrl: './tcfactordescuento-detail.component.html',
})
export class TCFACTORDESCUENTODetailComponent implements OnInit {
  tCFACTORDESCUENTO: ITCFACTORDESCUENTO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCFACTORDESCUENTO }) => {
      this.tCFACTORDESCUENTO = tCFACTORDESCUENTO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
