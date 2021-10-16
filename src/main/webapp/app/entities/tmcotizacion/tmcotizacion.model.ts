import * as dayjs from 'dayjs';

export interface ITMCOTIZACION {
  idCotizacion?: number;
  usuario?: number;
  fechaRegistro?: dayjs.Dayjs;
  estatus?: number;
  eliminada?: number;
}

export class TMCOTIZACION implements ITMCOTIZACION {
  constructor(
    public idCotizacion?: number,
    public usuario?: number,
    public fechaRegistro?: dayjs.Dayjs,
    public estatus?: number,
    public eliminada?: number
  ) {}
}

export function getTMCOTIZACIONIdentifier(tMCOTIZACION: ITMCOTIZACION): number | undefined {
  return tMCOTIZACION.idCotizacion;
}
