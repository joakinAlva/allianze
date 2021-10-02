export interface ITCDESCUENTOSUMAASEGURADA {
  idDescuentoSumaAsegurada?: number;
  minSuma?: string;
  maxSuma?: string;
  porcentaje?: number;
}

export class TCDESCUENTOSUMAASEGURADA implements ITCDESCUENTOSUMAASEGURADA {
  constructor(public idDescuentoSumaAsegurada?: number, public minSuma?: string, public maxSuma?: string, public porcentaje?: number) {}
}

export function getTCDESCUENTOSUMAASEGURADAIdentifier(tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA): number | undefined {
  return tCDESCUENTOSUMAASEGURADA.idDescuentoSumaAsegurada;
}
