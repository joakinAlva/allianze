import * as dayjs from 'dayjs';

export interface ITCTIPOPRODUCTO {
  idTipoProducto?: number;
  tipoProducto?: string;
  registro?: string;
  fecha?: dayjs.Dayjs;
}

export class TCTIPOPRODUCTO implements ITCTIPOPRODUCTO {
  constructor(public idTipoProducto?: number, public tipoProducto?: string, public registro?: string, public fecha?: dayjs.Dayjs) {}
}

export function getTCTIPOPRODUCTOIdentifier(tCTIPOPRODUCTO: ITCTIPOPRODUCTO): number | undefined {
  return tCTIPOPRODUCTO.idTipoProducto;
}
