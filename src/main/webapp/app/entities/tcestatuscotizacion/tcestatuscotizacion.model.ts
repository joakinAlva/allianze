export interface ITCESTATUSCOTIZACION {
  idEstatusCotizacion?: number;
  orden?: number;
  estatusCotizacion?: string;
}

export class TCESTATUSCOTIZACION implements ITCESTATUSCOTIZACION {
  constructor(public idEstatusCotizacion?: number, public orden?: number, public estatusCotizacion?: string) {}
}

export function getTCESTATUSCOTIZACIONIdentifier(tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION): number | undefined {
  return tCESTATUSCOTIZACION.idEstatusCotizacion;
}
