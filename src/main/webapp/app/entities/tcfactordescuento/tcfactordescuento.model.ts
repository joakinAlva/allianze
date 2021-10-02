export interface ITCFACTORDESCUENTO {
  idFactorDescuento?: number;
  factor?: number;
  porcentaje?: number;
}

export class TCFACTORDESCUENTO implements ITCFACTORDESCUENTO {
  constructor(public idFactorDescuento?: number, public factor?: number, public porcentaje?: number) {}
}

export function getTCFACTORDESCUENTOIdentifier(tCFACTORDESCUENTO: ITCFACTORDESCUENTO): number | undefined {
  return tCFACTORDESCUENTO.idFactorDescuento;
}
