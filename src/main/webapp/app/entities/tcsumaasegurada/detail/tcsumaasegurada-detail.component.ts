import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCSUMAASEGURADA } from '../tcsumaasegurada.model';

@Component({
  selector: 'jhi-tcsumaasegurada-detail',
  templateUrl: './tcsumaasegurada-detail.component.html',
})
export class TCSUMAASEGURADADetailComponent implements OnInit {
  tCSUMAASEGURADA: ITCSUMAASEGURADA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCSUMAASEGURADA }) => {
      this.tCSUMAASEGURADA = tCSUMAASEGURADA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
