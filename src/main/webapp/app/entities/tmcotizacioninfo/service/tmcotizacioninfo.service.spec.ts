import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITMCOTIZACIONINFO, TMCOTIZACIONINFO } from '../tmcotizacioninfo.model';

import { TMCOTIZACIONINFOService } from './tmcotizacioninfo.service';

describe('Service Tests', () => {
  describe('TMCOTIZACIONINFO Service', () => {
    let service: TMCOTIZACIONINFOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITMCOTIZACIONINFO;
    let expectedResult: ITMCOTIZACIONINFO | ITMCOTIZACIONINFO[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TMCOTIZACIONINFOService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        idCotizacionInfo: 0,
        numero: 0,
        cotizacion: 0,
        region: 0,
        fechaSolicitud: currentDate,
        responsable: 0,
        fechaEntrega: currentDate,
        contratante: 0,
        tipoUno: 0,
        tipoDos: 0,
        poliza: 0,
        inicioVigencia: currentDate,
        finVigencia: currentDate,
        intermediario: 'AAAAAAA',
        rfc: 'AAAAAAA',
        ejecutivo: 0,
        suscriptor: 0,
        plan: 0,
        ocupacion: 0,
        primaCovidVida: 0,
        primaCovidGff: 0,
        descuentoPrimaCovid: 0,
        extraPrimaVida: 0,
        extraPrimaGff: 0,
        porcentajeExtraPrimaVida: 0,
        porcentajeExtraPrimaGff: 0,
        valorFtr: 0,
        sami: 0,
        samiMin: 0,
        samiMax: 0,
        margen: 0,
        margenMin: 0,
        margenMax: 0,
        comision: 0,
        comisionMin: 0,
        comisionMax: 0,
        descuento: 0,
        descuentoMin: 0,
        descuentoMax: 0,
        primaneta: 0,
        primaNetaMin: 0,
        primaNetaMax: 0,
        derechoPoliza: 0,
        derechoPolizaMin: 0,
        derechoPolizaMax: 0,
        mayores: 0,
        mayoresMin: 0,
        mayoresMax: 0,
        asegurados: 0,
        aseguradosMin: 0,
        aseguradosMax: 0,
        saPromedio: 0,
        saPromedioMin: 0,
        saPromedioMax: 0,
        varSa: 0,
        varSaMin: 0,
        varSaMax: 0,
        saTotal: 0,
        saMaxima: 0,
        saMaximaMin: 0,
        saMaximaMax: 0,
        subgrupos: 0,
        subgruposMin: 0,
        edadMediaMin: 0,
        edadActuarial: 0,
        edadActuarialMin: 0,
        edadActPond: 0,
        edadActPondMin: 0,
        edadMin: 0,
        edadMinDos: 0,
        edadMaxDos: 0,
        edadMaxTres: 0,
        coeficienteVariacion: 0,
        factorTrUnoGiro: 0,
        factorSaProm: 0,
        pNetaGlobalSdmc: 0,
        pNetaGlobalCdmcCuota: 0,
        pNetaGlobalSmccdesc: 0,
        pNetaGlobalSmccdescCuota: 0,
        pNetaBasicaSdmc: 0,
        pNetaBasicaSdmcCuota: 0,
        pNetaBasicaCdmc: 0,
        pNetaBasicaCdmcCuota: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaSolicitud: currentDate.format(DATE_FORMAT),
            fechaEntrega: currentDate.format(DATE_FORMAT),
            inicioVigencia: currentDate.format(DATE_FORMAT),
            finVigencia: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TMCOTIZACIONINFO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaSolicitud: currentDate.format(DATE_FORMAT),
            fechaEntrega: currentDate.format(DATE_FORMAT),
            inicioVigencia: currentDate.format(DATE_FORMAT),
            finVigencia: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaSolicitud: currentDate,
            fechaEntrega: currentDate,
            inicioVigencia: currentDate,
            finVigencia: currentDate,
          },
          returnedFromService
        );

        service.create(new TMCOTIZACIONINFO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TMCOTIZACIONINFO', () => {
        const returnedFromService = Object.assign(
          {
            idCotizacionInfo: 1,
            numero: 1,
            cotizacion: 1,
            region: 1,
            fechaSolicitud: currentDate.format(DATE_FORMAT),
            responsable: 1,
            fechaEntrega: currentDate.format(DATE_FORMAT),
            contratante: 1,
            tipoUno: 1,
            tipoDos: 1,
            poliza: 1,
            inicioVigencia: currentDate.format(DATE_FORMAT),
            finVigencia: currentDate.format(DATE_FORMAT),
            intermediario: 'BBBBBB',
            rfc: 'BBBBBB',
            ejecutivo: 1,
            suscriptor: 1,
            plan: 1,
            ocupacion: 1,
            primaCovidVida: 1,
            primaCovidGff: 1,
            descuentoPrimaCovid: 1,
            extraPrimaVida: 1,
            extraPrimaGff: 1,
            porcentajeExtraPrimaVida: 1,
            porcentajeExtraPrimaGff: 1,
            valorFtr: 1,
            sami: 1,
            samiMin: 1,
            samiMax: 1,
            margen: 1,
            margenMin: 1,
            margenMax: 1,
            comision: 1,
            comisionMin: 1,
            comisionMax: 1,
            descuento: 1,
            descuentoMin: 1,
            descuentoMax: 1,
            primaneta: 1,
            primaNetaMin: 1,
            primaNetaMax: 1,
            derechoPoliza: 1,
            derechoPolizaMin: 1,
            derechoPolizaMax: 1,
            mayores: 1,
            mayoresMin: 1,
            mayoresMax: 1,
            asegurados: 1,
            aseguradosMin: 1,
            aseguradosMax: 1,
            saPromedio: 1,
            saPromedioMin: 1,
            saPromedioMax: 1,
            varSa: 1,
            varSaMin: 1,
            varSaMax: 1,
            saTotal: 1,
            saMaxima: 1,
            saMaximaMin: 1,
            saMaximaMax: 1,
            subgrupos: 1,
            subgruposMin: 1,
            edadMediaMin: 1,
            edadActuarial: 1,
            edadActuarialMin: 1,
            edadActPond: 1,
            edadActPondMin: 1,
            edadMin: 1,
            edadMinDos: 1,
            edadMaxDos: 1,
            edadMaxTres: 1,
            coeficienteVariacion: 1,
            factorTrUnoGiro: 1,
            factorSaProm: 1,
            pNetaGlobalSdmc: 1,
            pNetaGlobalCdmcCuota: 1,
            pNetaGlobalSmccdesc: 1,
            pNetaGlobalSmccdescCuota: 1,
            pNetaBasicaSdmc: 1,
            pNetaBasicaSdmcCuota: 1,
            pNetaBasicaCdmc: 1,
            pNetaBasicaCdmcCuota: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaSolicitud: currentDate,
            fechaEntrega: currentDate,
            inicioVigencia: currentDate,
            finVigencia: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TMCOTIZACIONINFO', () => {
        const patchObject = Object.assign(
          {
            numero: 1,
            cotizacion: 1,
            fechaEntrega: currentDate.format(DATE_FORMAT),
            tipoUno: 1,
            tipoDos: 1,
            inicioVigencia: currentDate.format(DATE_FORMAT),
            intermediario: 'BBBBBB',
            extraPrimaVida: 1,
            samiMin: 1,
            margenMin: 1,
            margenMax: 1,
            comision: 1,
            primaNetaMin: 1,
            derechoPoliza: 1,
            derechoPolizaMax: 1,
            mayores: 1,
            mayoresMax: 1,
            asegurados: 1,
            aseguradosMax: 1,
            saPromedioMax: 1,
            varSaMax: 1,
            subgrupos: 1,
            edadActPond: 1,
            edadMin: 1,
            edadMinDos: 1,
            edadMaxTres: 1,
            coeficienteVariacion: 1,
            factorTrUnoGiro: 1,
            pNetaGlobalCdmcCuota: 1,
            pNetaGlobalSmccdesc: 1,
            pNetaBasicaSdmc: 1,
            pNetaBasicaCdmc: 1,
          },
          new TMCOTIZACIONINFO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            fechaSolicitud: currentDate,
            fechaEntrega: currentDate,
            inicioVigencia: currentDate,
            finVigencia: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TMCOTIZACIONINFO', () => {
        const returnedFromService = Object.assign(
          {
            idCotizacionInfo: 1,
            numero: 1,
            cotizacion: 1,
            region: 1,
            fechaSolicitud: currentDate.format(DATE_FORMAT),
            responsable: 1,
            fechaEntrega: currentDate.format(DATE_FORMAT),
            contratante: 1,
            tipoUno: 1,
            tipoDos: 1,
            poliza: 1,
            inicioVigencia: currentDate.format(DATE_FORMAT),
            finVigencia: currentDate.format(DATE_FORMAT),
            intermediario: 'BBBBBB',
            rfc: 'BBBBBB',
            ejecutivo: 1,
            suscriptor: 1,
            plan: 1,
            ocupacion: 1,
            primaCovidVida: 1,
            primaCovidGff: 1,
            descuentoPrimaCovid: 1,
            extraPrimaVida: 1,
            extraPrimaGff: 1,
            porcentajeExtraPrimaVida: 1,
            porcentajeExtraPrimaGff: 1,
            valorFtr: 1,
            sami: 1,
            samiMin: 1,
            samiMax: 1,
            margen: 1,
            margenMin: 1,
            margenMax: 1,
            comision: 1,
            comisionMin: 1,
            comisionMax: 1,
            descuento: 1,
            descuentoMin: 1,
            descuentoMax: 1,
            primaneta: 1,
            primaNetaMin: 1,
            primaNetaMax: 1,
            derechoPoliza: 1,
            derechoPolizaMin: 1,
            derechoPolizaMax: 1,
            mayores: 1,
            mayoresMin: 1,
            mayoresMax: 1,
            asegurados: 1,
            aseguradosMin: 1,
            aseguradosMax: 1,
            saPromedio: 1,
            saPromedioMin: 1,
            saPromedioMax: 1,
            varSa: 1,
            varSaMin: 1,
            varSaMax: 1,
            saTotal: 1,
            saMaxima: 1,
            saMaximaMin: 1,
            saMaximaMax: 1,
            subgrupos: 1,
            subgruposMin: 1,
            edadMediaMin: 1,
            edadActuarial: 1,
            edadActuarialMin: 1,
            edadActPond: 1,
            edadActPondMin: 1,
            edadMin: 1,
            edadMinDos: 1,
            edadMaxDos: 1,
            edadMaxTres: 1,
            coeficienteVariacion: 1,
            factorTrUnoGiro: 1,
            factorSaProm: 1,
            pNetaGlobalSdmc: 1,
            pNetaGlobalCdmcCuota: 1,
            pNetaGlobalSmccdesc: 1,
            pNetaGlobalSmccdescCuota: 1,
            pNetaBasicaSdmc: 1,
            pNetaBasicaSdmcCuota: 1,
            pNetaBasicaCdmc: 1,
            pNetaBasicaCdmcCuota: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaSolicitud: currentDate,
            fechaEntrega: currentDate,
            inicioVigencia: currentDate,
            finVigencia: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TMCOTIZACIONINFO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTMCOTIZACIONINFOToCollectionIfMissing', () => {
        it('should add a TMCOTIZACIONINFO to an empty array', () => {
          const tMCOTIZACIONINFO: ITMCOTIZACIONINFO = { idCotizacionInfo: 123 };
          expectedResult = service.addTMCOTIZACIONINFOToCollectionIfMissing([], tMCOTIZACIONINFO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMCOTIZACIONINFO);
        });

        it('should not add a TMCOTIZACIONINFO to an array that contains it', () => {
          const tMCOTIZACIONINFO: ITMCOTIZACIONINFO = { idCotizacionInfo: 123 };
          const tMCOTIZACIONINFOCollection: ITMCOTIZACIONINFO[] = [
            {
              ...tMCOTIZACIONINFO,
            },
            { idCotizacionInfo: 456 },
          ];
          expectedResult = service.addTMCOTIZACIONINFOToCollectionIfMissing(tMCOTIZACIONINFOCollection, tMCOTIZACIONINFO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TMCOTIZACIONINFO to an array that doesn't contain it", () => {
          const tMCOTIZACIONINFO: ITMCOTIZACIONINFO = { idCotizacionInfo: 123 };
          const tMCOTIZACIONINFOCollection: ITMCOTIZACIONINFO[] = [{ idCotizacionInfo: 456 }];
          expectedResult = service.addTMCOTIZACIONINFOToCollectionIfMissing(tMCOTIZACIONINFOCollection, tMCOTIZACIONINFO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMCOTIZACIONINFO);
        });

        it('should add only unique TMCOTIZACIONINFO to an array', () => {
          const tMCOTIZACIONINFOArray: ITMCOTIZACIONINFO[] = [
            { idCotizacionInfo: 123 },
            { idCotizacionInfo: 456 },
            { idCotizacionInfo: 63137 },
          ];
          const tMCOTIZACIONINFOCollection: ITMCOTIZACIONINFO[] = [{ idCotizacionInfo: 123 }];
          expectedResult = service.addTMCOTIZACIONINFOToCollectionIfMissing(tMCOTIZACIONINFOCollection, ...tMCOTIZACIONINFOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tMCOTIZACIONINFO: ITMCOTIZACIONINFO = { idCotizacionInfo: 123 };
          const tMCOTIZACIONINFO2: ITMCOTIZACIONINFO = { idCotizacionInfo: 456 };
          expectedResult = service.addTMCOTIZACIONINFOToCollectionIfMissing([], tMCOTIZACIONINFO, tMCOTIZACIONINFO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMCOTIZACIONINFO);
          expect(expectedResult).toContain(tMCOTIZACIONINFO2);
        });

        it('should accept null and undefined values', () => {
          const tMCOTIZACIONINFO: ITMCOTIZACIONINFO = { idCotizacionInfo: 123 };
          expectedResult = service.addTMCOTIZACIONINFOToCollectionIfMissing([], null, tMCOTIZACIONINFO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMCOTIZACIONINFO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
