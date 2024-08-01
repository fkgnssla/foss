import { TMentorInfo } from './type';

export interface CalendarEvent {
  title: string;
  allDay: boolean;
  start: Date;
  end: Date;
  desc: string;
  applyCount: number;
  resourceId?: string;
  tooltip?: string;
  mentorId: number;
  calenderType?: number;
  profileImg?: string;
}

export type TMenteeCalendar = {
  mentorInfo: {
    selfProduce: string;
    fileUrl: string;
  } & TMentorInfo;

  scheduleInfos: Array<IMenteeCalendar<TMenteeSchedule>>;
};

export type TMypageMenteeCalendar = {
  time: string;
  scheduleId: number;
  mentorInfo: TMentorInfo;
};

// 제네릭 클래스로 만들어서 좀... 범용성을 늘리기
export interface IMenteeCalendar<T> {
  day: string;
  // 여기에 이렇게 넣기 Array<TRegisterMenteeCalender> | Array<TMenteeCalendar>
  schedules: Array<T>;
}

// 멘토 마이페이지에서 확인할 때 필요한 타입들
export interface IMentorCalender {
  day: string;
  schedules: Array<Tschedules>;
}

export type TMenteeSchedule = {
  time: string;
  scheduleId: number;
};

export type Tschedules = {
  applies: Array<Tappliers>;
} & TMenteeSchedule;

type Tappliers = {
  memberId: number;
  name: string;
  fileUrl: string;
};
