package com.jobs.application;

import com.jobs.domain.IRPF;

public class IRPFFactory {
	public static IRPF createIRPFBoss() {
		return new IRPF() {
			@Override
			public double pay(double salaryPerMonthGross) {
				return salaryPerMonthGross * 0.68;
			}
		};
	}
	
	public static IRPF createIRPFManager() {
		return new IRPF() {
			@Override
			public double pay(double salaryPerMonthGross) {
				return salaryPerMonthGross * 0.74;
			}
		};
	}

	public static IRPF createIRPFSenior() {
		return new IRPF() {
			@Override
			public double pay(double salaryPerMonthGross) {
				return salaryPerMonthGross * 0.76;
			}
		};
	}
	
	public static IRPF createIRPFMid() {
		return new IRPF() {
			@Override
			public double pay(double salaryPerMonthGross) {
				return salaryPerMonthGross * 0.85;
			}
		};
	}
	
	public static IRPF createIRPFJunior() {
		return new IRPF() {
			@Override
			public double pay(double salaryPerMonthGross) {
				return salaryPerMonthGross * 0.98;
			}
		};
	}
}
