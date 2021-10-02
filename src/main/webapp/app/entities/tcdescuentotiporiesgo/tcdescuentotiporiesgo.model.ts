export interface ITCDESCUENTOTIPORIESGO {
  idDescuentoTipoRiesgo?: number;
  tipo?: string;
  descuento?: number;
}

export class TCDESCUENTOTIPORIESGO implements ITCDESCUENTOTIPORIESGO {
  constructor(public idDescuentoTipoRiesgo?: number, public tipo?: string, public descuento?: number) {}
}

export function getTCDESCUENTOTIPORIESGOIdentifier(tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO): number | undefined {
  return tCDESCUENTOTIPORIESGO.idDescuentoTipoRiesgo;
}
