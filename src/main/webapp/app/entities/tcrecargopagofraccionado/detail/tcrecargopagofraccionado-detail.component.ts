import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCRECARGOPAGOFRACCIONADO } from '../tcrecargopagofraccionado.model';

@Component({
  selector: 'jhi-tcrecargopagofraccionado-detail',
  templateUrl: './tcrecargopagofraccionado-detail.component.html',
})
export class TCRECARGOPAGOFRACCIONADODetailComponent implements OnInit {
  tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCRECARGOPAGOFRACCIONADO }) => {
      this.tCRECARGOPAGOFRACCIONADO = tCRECARGOPAGOFRACCIONADO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
