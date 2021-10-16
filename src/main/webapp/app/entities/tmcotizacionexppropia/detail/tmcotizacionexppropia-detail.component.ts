import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITMCOTIZACIONEXPPROPIA } from '../tmcotizacionexppropia.model';

@Component({
  selector: 'jhi-tmcotizacionexppropia-detail',
  templateUrl: './tmcotizacionexppropia-detail.component.html',
})
export class TMCOTIZACIONEXPPROPIADetailComponent implements OnInit {
  tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMCOTIZACIONEXPPROPIA }) => {
      this.tMCOTIZACIONEXPPROPIA = tMCOTIZACIONEXPPROPIA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
