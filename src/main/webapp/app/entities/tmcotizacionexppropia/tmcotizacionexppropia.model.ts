export interface ITMCOTIZACIONEXPPROPIA {
  idCotizacionExpPropia?: number;
  numero?: number;
  periodo?: number;
  siniestro?: number;
  asegurados?: number;
  valorQx?: number;
}

export class TMCOTIZACIONEXPPROPIA implements ITMCOTIZACIONEXPPROPIA {
  constructor(
    public idCotizacionExpPropia?: number,
    public numero?: number,
    public periodo?: number,
    public siniestro?: number,
    public asegurados?: number,
    public valorQx?: number
  ) {}
}

export function getTMCOTIZACIONEXPPROPIAIdentifier(tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA): number | undefined {
  return tMCOTIZACIONEXPPROPIA.idCotizacionExpPropia;
}
